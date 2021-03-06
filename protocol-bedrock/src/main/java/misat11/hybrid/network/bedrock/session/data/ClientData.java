package misat11.hybrid.network.bedrock.session.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;
import misat11.hybrid.util.data.DeviceOS;

import java.util.UUID;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientData {
    @JsonProperty("TenantId")
    private final String tenantId;
    @JsonProperty("ADRole")
    private final String activeDirectoryRole;
    @JsonProperty("CapeData")
    private final byte[] capeData; // deserialized
    @JsonProperty("ClientRandomId")
    private final long clientRandomId;
    @JsonProperty("CurrentInputMode")
    private final int currentInputMode;
    @JsonProperty("DefaultInputMode")
    private final int defaultInputMode;
    @JsonProperty("DeviceId")
    private final String deviceId;
    @JsonProperty("DeviceModel")
    private final String deviceModel;
    @JsonProperty("DeviceOS")
    private final DeviceOS deviceOs;
    @JsonProperty("GameVersion")
    private final String gameVersion;
    @JsonProperty("GuiScale")
    private final int guiScale;
    @JsonProperty("IsEduMode")
    private final boolean educationMode;
    @JsonProperty("LanguageCode")
    private final String languageCode;
    @JsonProperty("PlatformOfflineId")
    private final String platformOfflineId;
    @JsonProperty("PlatformOnlineId")
    private final String platformOnlineId;
    @JsonProperty("PremiumSkin")
    private final boolean premiumSkin;
    @JsonProperty("SelfSignedId")
    private final UUID selfSignedId;
    @JsonProperty("ServerAddress")
    private final String serverAddress;
    @JsonProperty("SkinData")
    private final byte[] skinData; // deserialized
    @JsonProperty("SkinGeometry")
    private final byte[] skinGeometry; // deserialized
    @JsonProperty("SkinGeometryName")
    private final String skinGeometryName;
    @JsonProperty("SkinId")
    private final String skinId;
    @JsonProperty("ThirdPartyName")
    private final String thirdPartyName;
    @JsonProperty("UIProfile")
    private final int uiProfile;
}
