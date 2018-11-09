package com.nukkitx.server.level;

import com.flowpowered.math.vector.Vector3f;
import com.nukkitx.api.level.LevelSettings;
import com.nukkitx.api.level.data.Difficulty;
import com.nukkitx.api.level.data.Dimension;
import com.nukkitx.api.level.data.Generator;
import com.nukkitx.api.permission.PlayerPermission;
import com.nukkitx.api.util.GameMode;
import com.nukkitx.server.permission.NukkitAbilities;
import lombok.Data;

@Data
public abstract class NukkitLevelData implements LevelSettings {
    protected static NukkitLevelData defaults;

    // Level Settings
    private int seed;
    private long savedTick;
    private int savedTime;
    private Generator generator;
    private Dimension dimension;
    private NukkitGameRules gameRules = NukkitGameRules.getDefault();
    private GameMode gameMode = GameMode.SURVIVAL;
    private Difficulty difficulty = Difficulty.NORMAL;
    private Vector3f defaultSpawn;
    private boolean achievementsDisabled = false;
    private boolean eduWorld = false;
    private boolean eduFeaturesEnabled = true;
    private float rainLevel = 0;
    private float lightningLevel = 0;
    private boolean forceGameType = false;
    private boolean multiplayerGame = true;
    private boolean broadcastingToLan = true;
    private boolean broadcastingToXBL = true;
    private boolean commandsEnabled = true;
    private boolean texturepacksRequired = false;
    private boolean bonusChestEnabled = false;
    private boolean startingWithMap = false;
    private boolean trustingPlayers = false;
    private NukkitAbilities defaultAbilities;
    private PlayerPermission defaultPlayerPermission = PlayerPermission.MEMBER;
    private int XBLBroadcastMode = 3;
    private int serverChunkTickRange = 8; // Simulated render distance.
    private boolean broadcastingToPlatform = true;
    private int platformBroadcastMode = 0;
    private boolean intentOnXBLBroadcast = true;
    private boolean behaviorPackLocked = false;
    private boolean resourcePackLocked = false;
    private boolean fromLockedWorldTemplate = false;
    private boolean usingMsaGamertagsOnly = false;


    protected NukkitLevelData() {
        this.seed = 12345;
        this.generator = Generator.OVERWORLD;
        this.savedTick = 0;
        this.savedTime = 0;
        this.dimension = Dimension.OVERWORLD;
    }

    protected NukkitLevelData(long randomSeed, long tick, long time, Generator generator) {
        this.seed = 12345;
        this.savedTick = tick;
        this.savedTime = (int) time;
        this.generator = generator;
        this.dimension = Dimension.OVERWORLD;
    }

    public int getTime() {
        return savedTime;
    }
}
