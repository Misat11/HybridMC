package misat11.hybrid.level;

import com.flowpowered.math.vector.Vector3f;

import lombok.Data;
import misat11.hybrid.level.data.Difficulty;
import misat11.hybrid.level.data.Dimension;
import misat11.hybrid.level.data.Generator;
import misat11.hybrid.permission.NukkitAbilities;
import misat11.hybrid.permission.PlayerPermission;
import misat11.hybrid.util.GameMode;

@Data
public class LevelSettings {
    // Level Settings
    private int seed;
    private long savedTick;
    private int savedTime;
    private Generator generator = Generator.OVERWORLD;
    private Dimension dimension = Dimension.OVERWORLD;
    private NukkitGameRules gameRules = NukkitGameRules.getDefault();
    private GameMode gameMode = GameMode.SURVIVAL;
    private Difficulty difficulty = Difficulty.NORMAL;
    private Vector3f defaultSpawn;
    private boolean achievementsDisabled = false;
    private boolean eduWorld = false;
    private boolean eduFeaturesEnabled = false;
    private float rainLevel = 0;
    private float lightningLevel = 0;
    private boolean forceGameType = false;
    private boolean multiplayerGame = true;
    private boolean broadcastingToLan = false;
    private boolean broadcastingToXBL = false;
    private boolean commandsEnabled = true;
    private boolean texturepacksRequired = false;
    private boolean bonusChestEnabled = false;
    private boolean startingWithMap = false;
    private boolean trustingPlayers = false;
    private NukkitAbilities defaultAbilities;
    private PlayerPermission defaultPlayerPermission = PlayerPermission.MEMBER;
    private int XBLBroadcastMode = 4;
    private int serverChunkTickRange = 8; // Simulated render distance.
    private boolean broadcastingToPlatform = false;
    private int platformBroadcastMode = 0;
    private boolean intentOnXBLBroadcast = false;
    private boolean behaviorPackLocked = false;
    private boolean resourcePackLocked = false;
    private boolean fromLockedWorldTemplate = false;
    private boolean usingMsaGamertagsOnly = false;
    private int time = 0;
}
