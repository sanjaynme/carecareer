package au.com.carecareers.android.profileModule.professionRole.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import au.com.carecareers.android.base.model.BaseListResponse;

/**
 * Created by Nischal Manandhar on 01/12/2017.
 */

public class ProfessionRoleResponse extends BaseListResponse {
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

        @SerializedName("professions")
        @Expose
        private List<Profession> professions = null;

        public List<Profession> getProfessions() {
            return professions;
        }

        public void setProfessions(List<Profession> professions) {
            this.professions = professions;
        }
    }

    public static class Profession {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("active")
        @Expose
        private Integer active;
        @SerializedName("position")
        @Expose
        private Integer position;
        @SerializedName("_links")
        @Expose
        private Links links;
        @SerializedName("_embedded")
        @Expose
        private EmbeddedRole embedded;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public Integer getActive() {
            return active;
        }

        public void setActive(Integer active) {
            this.active = active;
        }

        public Integer getPosition() {
            return position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public Links getLinks() {
            return links;
        }

        public void setLinks(Links links) {
            this.links = links;
        }

        public EmbeddedRole getEmbedded() {
            return embedded;
        }

        public void setEmbedded(EmbeddedRole embedded) {
            this.embedded = embedded;
        }

    }

    public static class EmbeddedRole {

        @SerializedName("roles")
        @Expose
        private List<Role> roles = null;

        public List<Role> getRoles() {
            return roles;
        }

        public void setRoles(List<Role> roles) {
            this.roles = roles;
        }

    }

    public static class Role {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("profession_id")
        @Expose
        private Integer professionId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("active")
        @Expose
        private Integer active;
        @SerializedName("position")
        @Expose
        private Integer position;
        @SerializedName("_links")
        @Expose
        private Links links;

        private boolean isChecked;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getProfessionId() {
            return professionId;
        }

        public void setProfessionId(Integer professionId) {
            this.professionId = professionId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public Integer getActive() {
            return active;
        }

        public void setActive(Integer active) {
            this.active = active;
        }

        public Integer getPosition() {
            return position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public Links getLinks() {
            return links;
        }

        public void setLinks(Links links) {
            this.links = links;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }
    }
}
