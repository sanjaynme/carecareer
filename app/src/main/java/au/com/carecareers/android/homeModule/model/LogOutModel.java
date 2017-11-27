package au.com.carecareers.android.homeModule.model;

/**
 * Created by Sanjay on 11/27/2017.
 */

public class LogOutModel {
    public static class LogOutRequest {
        String token_type_hint;
        String token;

        public String getTokenTypeHint() {
            return token_type_hint;
        }

        public void setTokenTypeHint(String token_type_hint) {
            this.token_type_hint = token_type_hint;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public static class LogOutResponse {
        boolean revoked;

        public boolean getRevoked() {
            return revoked;
        }

        public void setRevoked(boolean revoked) {
            this.revoked = revoked;
        }
    }
}
