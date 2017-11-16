package au.com.carecareers.android.splashModule.model;

import android.util.Base64;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.UnsupportedEncodingException;

/**
 * Created by Nikesh on 11/16/2017.
 */

public class AuthenticationModel {
    public static String getBase64() {
        String clientId = "86e84f17-3fe0-4d3a-a2fd-a93af9ff3355";
        String clientSecret = "nxKQWgERJy9ALyDjGEp4bRw4";
        String base64 = "";
        String concatedString = clientId + ":" + clientSecret;
        byte[] data;
        try {
            data = concatedString.getBytes("UTF-8");
            base64 = Base64.encodeToString(data, Base64.NO_WRAP);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "Basic " + base64;
    }

    public static class AuthenticationResponse {
        @SerializedName("access_token")
        @Expose
        private String accessToken;
        @SerializedName("expires_in")
        @Expose
        private Integer expiresIn;
        @SerializedName("token_type")
        @Expose
        private String tokenType;
        @SerializedName("scope")
        @Expose
        private Object scope;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public Integer getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(Integer expiresIn) {
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
    }
}
