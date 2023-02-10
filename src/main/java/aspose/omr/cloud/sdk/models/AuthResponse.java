package aspose.omr.cloud.sdk.models;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @SerializedName("access_token")
    String accessToken = "";
    @SerializedName("token_type")
    String tokenType = "";
}
