package misat11.hybrid.network.java.p401.packet.ingame.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.game.UnlockRecipesAction;
import misat11.hybrid.network.java.pabstract.packet.MinecraftPacket;
import misat11.hybrid.network.java.pabstract.packet.ingame.server.ServerUnlockRecipesPacket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ServerUnlockRecipesPacket401 extends MinecraftPacket implements ServerUnlockRecipesPacket {
    private UnlockRecipesAction action;

    private List<String> recipes;
    private List<String> alreadyKnownRecipes;

    private boolean openCraftingBook;
    private boolean activateCraftingFiltering;
    private boolean openSmeltingBook;
    private boolean activateSmeltingFiltering;

    @SuppressWarnings("unused")
    private ServerUnlockRecipesPacket401() {
    }

    private ServerUnlockRecipesPacket401(boolean openCraftingBook, boolean activateCraftingFiltering,
                                      boolean openSmeltingBook, boolean activateSmeltingFiltering,
                                      List<String> recipes) {
        this.openCraftingBook = openCraftingBook;
        this.activateCraftingFiltering = activateCraftingFiltering;
        this.openSmeltingBook = openSmeltingBook;
        this.activateSmeltingFiltering = activateSmeltingFiltering;
        this.recipes = recipes;
    }

    public ServerUnlockRecipesPacket401(boolean openCraftingBook, boolean activateCraftingFiltering,
                                     boolean openSmeltingBook, boolean activateSmeltingFiltering,
                                     List<String> recipes, List<String> alreadyKnownRecipes) {
        this(openCraftingBook, activateCraftingFiltering, openSmeltingBook, activateSmeltingFiltering, recipes);
        this.action = UnlockRecipesAction.INIT;
        this.alreadyKnownRecipes = alreadyKnownRecipes;
    }

    public ServerUnlockRecipesPacket401(boolean openCraftingBook, boolean activateCraftingFiltering,
                                     boolean openSmeltingBook, boolean activateSmeltingFiltering,
                                     List<String> recipes, UnlockRecipesAction action) {
        this(openCraftingBook, activateCraftingFiltering, openSmeltingBook, activateSmeltingFiltering, recipes);
        if(action != UnlockRecipesAction.ADD && action != UnlockRecipesAction.REMOVE) {
            throw new IllegalArgumentException("action must be ADD or REMOVE");
        }
        this.action = action;
    }

    public boolean getOpenCraftingBook() {
        return this.openCraftingBook;
    }

    public boolean getActivateCraftingFiltering() {
        return this.activateCraftingFiltering;
    }

    public boolean getOpenSmeltingBook() {
        return this.openSmeltingBook;
    }

    public boolean getActivateSmeltingFiltering() {
        return this.activateSmeltingFiltering;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.action = getMagic().key(UnlockRecipesAction.class, in.readVarInt());

        this.openCraftingBook = in.readBoolean();
        this.activateCraftingFiltering = in.readBoolean();
        this.openSmeltingBook = in.readBoolean();
        this.activateSmeltingFiltering = in.readBoolean();

        if(this.action == UnlockRecipesAction.INIT) {
            int size = in.readVarInt();
            this.alreadyKnownRecipes = new ArrayList<>(size);
            for(int i = 0; i < size; i++) {
                this.alreadyKnownRecipes.add(in.readString());
            }
        }

        int size = in.readVarInt();
        this.recipes = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            this.recipes.add(in.readString());
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(getMagic().value(Integer.class, this.action));

        out.writeBoolean(this.openCraftingBook);
        out.writeBoolean(this.activateCraftingFiltering);
        out.writeBoolean(this.openSmeltingBook);
        out.writeBoolean(this.activateSmeltingFiltering);

        if(this.action == UnlockRecipesAction.INIT) {
            out.writeVarInt(this.alreadyKnownRecipes.size());
            for(String recipeId : this.alreadyKnownRecipes) {
                out.writeString(recipeId);
            }
        }

        out.writeVarInt(this.recipes.size());
        for(String recipeId : this.recipes) {
            out.writeString(recipeId);
        }
    }
}
