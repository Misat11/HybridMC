package misat11.hybrid.network.java.p404;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.auth.exception.request.RequestException;

import misat11.hybrid.network.java.p401.MinecraftProtocol401;
import misat11.hybrid.network.java.p404.util.NetUtil404;
import misat11.hybrid.network.java.pabstract.data.SubProtocol;
import misat11.hybrid.network.java.pabstract.util.NetUtil;

import java.net.Proxy;

public class MinecraftProtocol404 extends MinecraftProtocol401 {
	
    public MinecraftProtocol404(SubProtocol subProtocol) {
    	super(subProtocol);
    }

	public MinecraftProtocol404(String username) {
		super(username);
	}

    public MinecraftProtocol404(String username, String password) throws RequestException {
    	super(username, password);
    }

    public MinecraftProtocol404(String username, String clientToken, String accessToken) throws RequestException {
    	super(username, clientToken, accessToken);
    }

    public MinecraftProtocol404(String username, String password, Proxy proxy) throws RequestException {
    	super(username, password, proxy);
    }

    public MinecraftProtocol404(String username, String clientToken, String accessToken, Proxy proxy) throws RequestException {
    	super(username, clientToken, accessToken, proxy);
    }

    public MinecraftProtocol404(GameProfile profile, String clientToken, String accessToken) {
    	super(profile, clientToken, accessToken);
    }

	@Override
	public int getProtocolVersion() {
		return MinecraftConstants404.PROTOCOL_VERSION;
	}

	@Override
	public NetUtil getNetUtil() {
		return NetUtil404.INSTANCE;
	}
}
