package misat11.hybrid.downstream.cache;

import java.util.HashMap;

import com.flowpowered.math.vector.Vector3i;

import misat11.hybrid.network.java.pabstract.data.game.world.sound.BuiltinSound;

public class JukeboxCache {
	private final HashMap<Vector3i, BuiltinSound> jukebox = new HashMap<Vector3i, BuiltinSound>();
	
	public void registerJukebox(Vector3i position, BuiltinSound record) {
		jukebox.put(position, record);
	}
	
	public BuiltinSound unregisterJukebox(Vector3i position) {
		if(jukebox.containsKey(position)) {
			BuiltinSound sound = jukebox.get(position);
			jukebox.remove(position);
			return sound;
		}
		return null;
	}
}
