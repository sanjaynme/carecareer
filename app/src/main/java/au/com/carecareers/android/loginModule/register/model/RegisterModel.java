package au.com.carecareers.android.loginModule.register.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanjay on 11/15/2017.
 */

public class RegisterModel {
    public static class RegisterRequest {
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("subscribe")
        @Expose
        private Integer subscribe;
        @SerializedName("meta")
        @Expose
        private Meta meta;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Integer getSubscribe() {
            return subscribe;
        }

        public void setSubscribe(Integer subscribe) {
            this.subscribe = subscribe;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }

        public static class Meta {
            @SerializedName("state_id")
            @Expose
            private String stateId;

            public String getStateId() {
                return stateId;
            }

            public void setStateId(String stateId) {
                this.stateId = stateId;
            }
        }
    }

    public static class RegisterResponse {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("subscribe")
        @Expose
        private Integer subscribe;
        @SerializedName("active")
        @Expose
        private Integer active;
        @SerializedName("meta")
        @Expose
        private Meta meta;
        @SerializedName("_links")
        @Expose
        private Links links;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getSubscribe() {
            return subscribe;
        }

        public void setSubscribe(Integer subscribe) {
            this.subscribe = subscribe;
        }

        public Integer getActive() {
            return active;
        }

        public void setActive(Integer active) {
            this.active = active;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }

        public Links getLinks() {
            return links;
        }

        public void setLinks(Links links) {
            this.links = links;
        }

        private class Meta {
            @SerializedName("state_id")
            @Expose
            private Integer stateId;

            public Integer getStateId() {
                return stateId;
            }

            public void setStateId(Integer stateId) {
                this.stateId = stateId;
            }
        }

        private class Links {
            @SerializedName("self")
            @Expose
            private Self self;

            public Self getSelf() {
                return self;
            }

            public void setSelf(Self self) {
                this.self = self;
            }

            private class Self {
                @SerializedName("href")
                @Expose
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }
        }
    }
}
