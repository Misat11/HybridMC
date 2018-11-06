package misat11.hybrid.network;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.flowpowered.math.GenericMath;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.nukkitx.network.SessionManager;

import misat11.hybrid.network.bedrock.session.HybridSession;

public class HybridSessionManager extends SessionManager<HybridSession> {

    private static final int SESSIONS_PER_THREAD = 50;

    private final ThreadPoolExecutor sessionTicker = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("HybridMC Session Ticker - #%d").setDaemon(true).build());

	@Override
	protected void onAddSession(HybridSession session) {	
		adjustPoolSize();
	}
	
	@Override
	protected void onRemoveSession(HybridSession session) {
		adjustPoolSize();
	}
    
    private void adjustPoolSize() {
        int threads = GenericMath.clamp(sessions.size() / SESSIONS_PER_THREAD, 1, Runtime.getRuntime().availableProcessors());
        if (sessionTicker.getMaximumPoolSize() != threads) {
            sessionTicker.setMaximumPoolSize(threads);
        }
    }

    public void onTick() {
        for (HybridSession session : sessions.values()) {
            sessionTicker.execute(session::onTick);
        }
    }
    
    public ThreadPoolExecutor getSessionTicker() {
    	return sessionTicker;
    }
}
