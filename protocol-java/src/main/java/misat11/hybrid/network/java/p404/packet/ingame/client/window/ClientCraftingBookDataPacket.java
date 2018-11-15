package misat11.hybrid.network.java.p404.packet.ingame.client.window;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import misat11.hybrid.network.java.p404.data.MagicValues404;
import misat11.hybrid.network.java.pabstract.data.game.window.CraftingBookDataType;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;

import java.io.IOException;

public class ClientCraftingBookDataPacket extends MinecraftPacket {
    private CraftingBookDataType type;
    private String recipeId;
    private boolean craftingBookOpen;
    private boolean filterCraftingActive;
    private boolean smeltingBookOpen;
    private boolean filterSmeltingActive;

    @SuppressWarnings("unused")
    private ClientCraftingBookDataPacket() {
    }

    public ClientCraftingBookDataPacket(String recipeId) {
        this.type = CraftingBookDataType.DISPLAYED_RECIPE;
        this.recipeId = recipeId;
    }

    public ClientCraftingBookDataPacket(boolean craftingBookOpen, boolean filterCraftingActive,
                                        boolean smeltingBookOpen, boolean filterSmeltingActive) {
        this.type = CraftingBookDataType.CRAFTING_BOOK_STATUS;
        this.craftingBookOpen = craftingBookOpen;
        this.filterCraftingActive = filterCraftingActive;
    }

    public CraftingBookDataType getType() {
        return type;
    }

    private void ensureType(CraftingBookDataType type, String what) {
        if(this.type != type) {
            throw new IllegalStateException(what + " is only set when type is " + type + " but it is " + this.type);
        }
    }

    public String getRecipeId() {
        ensureType(CraftingBookDataType.DISPLAYED_RECIPE, "recipeId");
        return recipeId;
    }

    public boolean isCraftingBookOpen() {
        ensureType(CraftingBookDataType.CRAFTING_BOOK_STATUS, "craftingBookOpen");
        return craftingBookOpen;
    }

    public boolean isFilterCraftingActive() {
        ensureType(CraftingBookDataType.CRAFTING_BOOK_STATUS, "filterCraftingActive");
        return filterCraftingActive;
    }

    public boolean isSmeltingBookOpen() {
        ensureType(CraftingBookDataType.CRAFTING_BOOK_STATUS, "smeltingBookOpen");
        return smeltingBookOpen;
    }

    public boolean isFilterSmeltingActive() {
        ensureType(CraftingBookDataType.CRAFTING_BOOK_STATUS, "filterSmeltingActive");
        return filterSmeltingActive;
    }

    @Override
    public void read(NetInput in) throws IOException {
        switch(this.type = MagicValues404.key(CraftingBookDataType.class, in.readVarInt())) {
            case DISPLAYED_RECIPE:
                this.recipeId = in.readString();
                break;
            case CRAFTING_BOOK_STATUS:
                this.craftingBookOpen = in.readBoolean();
                this.filterCraftingActive = in.readBoolean();
                this.smeltingBookOpen = in.readBoolean();
                this.filterSmeltingActive = in.readBoolean();
                break;
            default:
                throw new IOException("Unknown crafting book data type: " + this.type);
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(MagicValues404.value(Integer.class, this.type));
        switch(this.type) {
            case DISPLAYED_RECIPE:
                out.writeString(this.recipeId);
                break;
            case CRAFTING_BOOK_STATUS:
                out.writeBoolean(this.craftingBookOpen);
                out.writeBoolean(this.filterCraftingActive);
                out.writeBoolean(this.smeltingBookOpen);
                out.writeBoolean(this.filterSmeltingActive);
                break;
            default:
                throw new IOException("Unknown crafting book data type: " + this.type);
        }
    }
}
