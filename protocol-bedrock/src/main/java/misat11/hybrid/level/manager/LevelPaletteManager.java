package misat11.hybrid.level.manager;

import com.fasterxml.jackson.databind.type.CollectionType;

import gnu.trove.map.TIntIntMap;
import gnu.trove.map.hash.TIntIntHashMap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.AllArgsConstructor;
import misat11.hybrid.NukkitServer;
import misat11.hybrid.network.bedrock.BedrockUtil;
import misat11.hybrid.network.util.VarInts;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LevelPaletteManager {
    private static final int RUNTIMEID_TABLE_CAPACITY = 4467;
    private final TIntIntMap legacyId2RuntimeId = new TIntIntHashMap(RUNTIMEID_TABLE_CAPACITY, 0.5f, -1, -1);
    private final ByteBuf cachedPallete;
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private int runtimeIdAllocator = 0;
    private final Lock w = rwl.writeLock();

    public LevelPaletteManager() {
        InputStream stream = NukkitServer.class.getClassLoader().getResourceAsStream("runtimeid_table.json");
        if (stream == null) {
            throw new AssertionError("Static RuntimeID table not found");
        }

        CollectionType type = NukkitServer.JSON_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, RuntimeEntry.class);
        ArrayList<RuntimeEntry> entries;
        try {
            entries = NukkitServer.JSON_MAPPER.readValue(stream, type);
        } catch (Exception e) {
            throw new AssertionError("Could not load RuntimeID table");
        }

        cachedPallete = Unpooled.buffer();
        VarInts.writeUnsignedInt(cachedPallete, entries.size());

        for (RuntimeEntry entry : entries) {
            registerRuntimeId((entry.id << 4) | entry.data);

            BedrockUtil.writeString(cachedPallete, entry.name);
            cachedPallete.writeShortLE(entry.data);
        }
    }

    public int fromLegacy(int id, byte data) {
        int runtimeId;
        if ((runtimeId = legacyId2RuntimeId.get((id << 4) | data)) == -1) {
            throw new IllegalArgumentException("Unknown legacy id");
        }
        return runtimeId;
    }

    private int registerRuntimeId(int legacyId) {
        if (legacyId2RuntimeId.containsKey(legacyId)) {
            throw new IllegalArgumentException("LegacyID already registered");
        }

        int runtimeId;

        w.lock();
        try {
            runtimeId = runtimeIdAllocator++;
            legacyId2RuntimeId.put(legacyId, runtimeId);
        } finally {
            w.unlock();
        }
        return runtimeId;
    }

    public ByteBuf getCachedPallete() {
        return cachedPallete;
    }

    @AllArgsConstructor
    private static class RuntimeEntry {
        private final String name;
        private final int id;
        private final int data;
    }
}
