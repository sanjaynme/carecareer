package au.com.carecareers.android.profileModule.locationArea.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import au.com.carecareers.android.profileModule.selectAvatar.model.AvatarResponse.Links;

/**
 * Created by Nischal Manandhar on 28/11/2017.
 */

public class LocationAreaResponse {
    @SerializedName("total_items")
    @Expose
    private Integer totalItems;
    @SerializedName("page_size")
    @Expose
    private Integer pageSize;
    @SerializedName("page_count")
    @Expose
    private Integer pageCount;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("_embedded")
    @Expose
    private EmbeddedLocation embedded;

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public EmbeddedLocation getEmbedded() {
        return this.embedded;
    }

    public void setEmbedded(EmbeddedLocation embedded) {
        this.embedded = embedded;
    }

    public static class EmbeddedLocation {

        @SerializedName("locations")
        @Expose
        private List<Location> locations = null;

        public List<Location> getLocations() {
            return locations;
        }

        public void setLocations(List<Location> locations) {
            this.locations = locations;
        }
    }

    public static class Location {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("country_id")
        @Expose
        private Integer countryId;
        @SerializedName("name")
        @Expose
        private String name;
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
        private EmbeddedArea embedded;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCountryId() {
            return countryId;
        }

        public void setCountryId(Integer countryId) {
            this.countryId = countryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public EmbeddedArea getEmbedded() {
            return this.embedded;
        }

        public void setEmbedded(EmbeddedArea embedded) {
            this.embedded = embedded;
        }
    }

    public static class EmbeddedArea {

        @SerializedName("areas")
        @Expose
        private List<Area> areas = null;

        public List<Area> getAreas() {
            return areas;
        }

        public void setAreas(List<Area> areas) {
            this.areas = areas;
        }
    }

    public static class Area {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("location_id")
        @Expose
        private Integer locationId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("active")
        @Expose
        private Integer active;
        @SerializedName("position")
        @Expose
        private Integer position;
        @SerializedName("_links")
        @Expose
        private Links links;
        @SerializedName("is_checked")
        @Expose
        private boolean isChecked;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getLocationId() {
            return locationId;
        }

        public void setLocationId(Integer locationId) {
            this.locationId = locationId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
