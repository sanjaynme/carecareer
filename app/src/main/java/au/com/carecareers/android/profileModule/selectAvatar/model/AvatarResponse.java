package au.com.carecareers.android.profileModule.selectAvatar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import au.com.carecareers.android.base.model.BaseListResponse;

public class AvatarResponse extends BaseListResponse {
    @SerializedName("_embedded")
    @Expose
    private Embedded embedded;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    public static class Embedded {
        @SerializedName("avatars")
        @Expose
        private List<Avatar> avatars = null;

        public List<Avatar> getAvatars() {
            return avatars;
        }

        public void setAvatars(List<Avatar> avatars) {
            this.avatars = avatars;
        }
    }

    public static class Avatar {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("active")
        @Expose
        private Integer active;
        @SerializedName("_links")
        @Expose
        private Links links;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getActive() {
            return active;
        }

        public void setActive(Integer active) {
            this.active = active;
        }

        public Links getLinks() {
            return links;
        }

        public void setLinks(Links links) {
            this.links = links;
        }
    }
}