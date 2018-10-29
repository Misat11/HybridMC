package misat11.hybrid.network;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.ImmutableList;
import com.nukkitx.network.SessionManager;

import misat11.hybrid.network.bedrock.session.HybridSession;

public class HybridSessionManager implements SessionManager<HybridSession>{
	
	private final ConcurrentMap<InetSocketAddress, HybridSession> sessions = new ConcurrentHashMap<InetSocketAddress, HybridSession>();

	@Override
	public boolean add(InetSocketAddress address, HybridSession session) {
		return sessions.putIfAbsent(address, session) == null;
	}

	@Override
	public boolean remove(HybridSession session) {
		return sessions.values().remove(session);
	}

	@Override
	public HybridSession get(InetSocketAddress address) {
		return sessions.get(address);
	}

	@Override
	public Collection<HybridSession> all() {
		return ImmutableList.copyOf(sessions.values());
	}

	@Override
	public int getCount() {
		return sessions.size();
	}

	@Override
	public void onTick() {
		sessions.values().forEach((session) -> session.onTick());
	}

}
