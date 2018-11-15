package misat11.hybrid.network.java.pabstract;

import java.net.Proxy;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.UUID;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.auth.exception.request.RequestException;
import com.github.steveice10.mc.auth.service.AuthenticationService;
import com.github.steveice10.packetlib.Client;
import com.github.steveice10.packetlib.Server;
import com.github.steveice10.packetlib.Session;
import com.github.steveice10.packetlib.crypt.AESEncryption;
import com.github.steveice10.packetlib.event.session.SessionListener;
import com.github.steveice10.packetlib.packet.DefaultPacketHeader;
import com.github.steveice10.packetlib.packet.PacketHeader;
import com.github.steveice10.packetlib.packet.PacketProtocol;

import lombok.Getter;
import misat11.hybrid.network.java.pabstract.data.SubProtocol;

@Getter
public abstract class MinecraftProtocolAbstract extends PacketProtocol {
	protected SubProtocol subProtocol = SubProtocol.HANDSHAKE;
	protected PacketHeader packetHeader = new DefaultPacketHeader();
	protected AESEncryption encryption;

	protected GameProfile profile;
	protected String clientToken = "";
	protected String accessToken = "";

	/* CONSTRUCTORS */
	public MinecraftProtocolAbstract(SubProtocol subProtocol) {
		if (subProtocol != SubProtocol.LOGIN && subProtocol != SubProtocol.STATUS) {
			throw new IllegalArgumentException("Only login and status modes are permitted.");
		}

		this.subProtocol = subProtocol;
		if (subProtocol == SubProtocol.LOGIN) {
			this.profile = new GameProfile((UUID) null, "Player");
		}
	}

	public MinecraftProtocolAbstract(String username) {
		this(SubProtocol.LOGIN);
		this.profile = new GameProfile((UUID) null, username);
	}

	public MinecraftProtocolAbstract(String username, String password) throws RequestException {
		this(username, password, Proxy.NO_PROXY);
	}

	public MinecraftProtocolAbstract(String username, String clientToken, String accessToken) throws RequestException {
		this(username, clientToken, accessToken, Proxy.NO_PROXY);
	}

	public MinecraftProtocolAbstract(String username, String password, Proxy proxy) throws RequestException {
		this(username, UUID.randomUUID().toString(), password, false, proxy);
	}

	public MinecraftProtocolAbstract(String username, String clientToken, String accessToken, Proxy proxy)
			throws RequestException {
		this(username, clientToken, accessToken, true, proxy);
	}

	private MinecraftProtocolAbstract(String username, String clientToken, String using, boolean token, Proxy authProxy)
			throws RequestException {
		this(SubProtocol.LOGIN);

		AuthenticationService auth = new AuthenticationService(clientToken, authProxy);
		auth.setUsername(username);
		if (token) {
			auth.setAccessToken(using);
		} else {
			auth.setPassword(using);
		}

		auth.login();
		this.profile = auth.getSelectedProfile();
		this.clientToken = auth.getClientToken();
		this.accessToken = auth.getAccessToken();
	}

	public MinecraftProtocolAbstract(GameProfile profile, String clientToken, String accessToken) {
		this(SubProtocol.LOGIN);
		this.profile = profile;
		this.clientToken = clientToken;
		this.accessToken = accessToken;
	}

	/* METHODS */
	@Override
	public String getSRVRecordPrefix() {
		return "_minecraft";
	}

    @Override
    public void newClientSession(Client client, Session session) {
        if(this.profile != null) {
            session.setFlag(MinecraftConstants.PROFILE_KEY, this.profile);
            session.setFlag(MinecraftConstants.ACCESS_TOKEN_KEY, this.accessToken);
        }

        this.setSubProtocol(this.subProtocol, session);
        session.addListener(this.createClientSession());
    }

	@Override
	public void newServerSession(Server server, Session session) {
		throw new UnsupportedOperationException("Server is not supported!");
	}

	public void enableEncryption(Key key) {
		try {
			this.encryption = new AESEncryption(key);
		} catch (GeneralSecurityException e) {
			throw new Error("Failed to enable protocol encryption.", e);
		}
	}

	public void setSubProtocol(SubProtocol subProtocol, Session session) {
		this.clearPackets();
		switch (subProtocol) {
		case HANDSHAKE:
			this.initClientHandshake(session);
			break;
		case LOGIN:
			this.initClientLogin(session);
			break;
		case GAME:
			this.initClientGame(session);
			break;
		case STATUS:
			this.initClientStatus(session);
			break;
		}

		this.subProtocol = subProtocol;
	}
	
	/* ABSTRACT METHODS */
	
	protected abstract SessionListener createClientSession();

	protected abstract void initClientHandshake(Session session);

	protected abstract void initClientLogin(Session session);

	protected abstract void initClientGame(Session session);

	protected abstract void initClientStatus(Session session);

}
