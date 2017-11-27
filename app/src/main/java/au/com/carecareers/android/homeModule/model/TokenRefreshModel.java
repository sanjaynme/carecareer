package au.com.carecareers.android.homeModule.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanjay on 11/27/2017.
 */

public class TokenRefreshModel {
    public static class TokenRefreshRequest {
        String refresh_token;
        String client_id;
        String client_secret;

        public String getRefreshToken() {
            return refresh_token;
        }

        public void setRefreshToken(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public String getClientId() {
            return client_id;
        }

        public void setClientId(String client_id) {
            this.client_id = client_id;
        }

        public String getClientSecret() {
            return client_secret;
        }

        public void setClientSecret(String client_secret) {
            this.client_secret = client_secret;
        }
    }

    public static class TokenRefreshResponse {
        @SerializedName("access_token")
        @Expose
        private String accessToken;
        @SerializedName("expires_in")
        @Expose
        private String expiresIn;
        @SerializedName("token_type")
        @Expose
        private String tokenType;
        @SerializedName("scope")
        @Expose
        private Object scope;
        @SerializedName("refresh_token")
        @Expose
        private String refreshToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(String expiresIn) {
            this.expiresIn = expiresIn;
        }

        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        public Object getScope() {
            return scope;
        }

        public void setScope(Object scope) {
            this.scope = scope;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }
}
