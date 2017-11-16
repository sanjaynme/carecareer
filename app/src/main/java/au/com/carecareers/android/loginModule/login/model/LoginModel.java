package au.com.carecareers.android.loginModule.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */

public class LoginModel {
    public class LoginRespones {

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
        @SerializedName("refresh_token")
        @Expose
        private String refreshToken;
        @SerializedName("user")
        @Expose
        private User user;

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

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public class User {
            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("first_name")
            @Expose
            private String firstName;
            @SerializedName("last_name")
            @Expose
            private String lastName;
            @SerializedName("last_login_date")
            @Expose
            private String lastLoginDate;
            @SerializedName("last_login_ipv4")
            @Expose
            private String lastLoginIpv4;
            @SerializedName("last_login_ipv6")
            @Expose
            private Object lastLoginIpv6;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }

            public String getLastLoginDate() {
                return lastLoginDate;
            }

            public void setLastLoginDate(String lastLoginDate) {
                this.lastLoginDate = lastLoginDate;
            }

            public String getLastLoginIpv4() {
                return lastLoginIpv4;
            }

            public void setLastLoginIpv4(String lastLoginIpv4) {
                this.lastLoginIpv4 = lastLoginIpv4;
            }

            public Object getLastLoginIpv6() {
                return lastLoginIpv6;
            }

            public void setLastLoginIpv6(Object lastLoginIpv6) {
                this.lastLoginIpv6 = lastLoginIpv6;
            }
        }
    }
}

