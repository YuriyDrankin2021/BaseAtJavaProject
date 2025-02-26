package data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class AuthData {
    @SerializedName("access_token")
    String accessToken;
    @SerializedName("expires_in")
    int expiresIn;
    @SerializedName("refresh_expires_in")
    String refreshExpiresIn;
    @SerializedName("refresh_token")
    String refreshToken;
}
