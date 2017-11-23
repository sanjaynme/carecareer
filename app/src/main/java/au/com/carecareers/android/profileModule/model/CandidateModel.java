package au.com.carecareers.android.profileModule.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nischal Manandhar on 21/11/2017.
 */

public class CandidateModel {
    @SerializedName("title")
    public String title;
    @SerializedName("first_name")
    public String firstName;
    @SerializedName("last_name")
    public String lastName;
    @SerializedName("email")
    public String email;
    @SerializedName("subscribe")
    public int subscribe;
    @SerializedName("phone_home")
    public String phoneHome;
    @SerializedName("mobile")
    public String mobile;
    @SerializedName("address")
    public Address address;
    @SerializedName("location_area")
    public LocationArea locationArea;
    @SerializedName("profession_role")
    public ProfessionRole professionRole;
    @SerializedName("work_type_availability")
    public WorkTypeAvailability workTypeAvailability;
    @SerializedName("meta")
    public Meta meta;

    public static class Address {
        @SerializedName("formatted")
        public String formatted;
        @SerializedName("address")
        public String address;
        @SerializedName("city")
        public String city;
        @SerializedName("state")
        public String state;
        @SerializedName("country")
        public String country;
        @SerializedName("postcode")
        public String postcode;
        @SerializedName("latitude")
        public String latitude;
        @SerializedName("longitude")
        public String longitude;
        @SerializedName("map_zoom")
        public int mapZoom;
    }

    public static class LocationArea {
        @SerializedName("country_id")
        public int countryId;
        @SerializedName("locations")
        public List<Object> locations;
        @SerializedName("areas")
        public List<Object> areas;
    }

    public static class ProfessionRole {
        @SerializedName("professions")
        public List<Object> professions;
        @SerializedName("roles")
        public List<Object> roles;
    }

    public static class WorkAvailabilities {
    }

    public static class WorkTypeAvailability {
        @SerializedName("work_types")
        public List<Object> workTypes;
        @SerializedName("work_availabilities")
        public WorkAvailabilities workAvailabilities;
    }

    public static class Meta {
    }
}
