package au.com.carecareers.android.jobAdsModule.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nikesh on 12/1/2017.
 */

public class JobAdsModel {
    public static class JobAdsResponse {
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
        private Embedded embedded;
        @SerializedName("sort")
        @Expose
        private String sort;
        @SerializedName("show_premiums")
        @Expose
        private Integer showPremiums;
        @SerializedName("job_types")
        @Expose
        public List<JobAdsTypes> jobTypes = null;
        @SerializedName("countries")
        @Expose
        private List<JobCountry> countries = null;
        @SerializedName("job_counts")
        @Expose
        private JobCounts jobCounts;

        public class JobAdsTypes {
            @SerializedName("job_type_id")
            @Expose
            private String jobTypeId;
        }

        public class Links {

            @SerializedName("self")
            @Expose
            private JobAdsSelf self;
            @SerializedName("first")
            @Expose
            private First first;
            @SerializedName("last")
            @Expose
            private Last last;
            @SerializedName("next")
            @Expose
            private Next next;

            public class JobAdsSelf {
                @SerializedName("href")
                @Expose
                public String href;
            }

            public class First {
                @SerializedName("href")
                @Expose
                public String href;
            }

            public class Last {
                @SerializedName("href")
                @Expose
                public String href;
            }

            private class Next {
                @SerializedName("href")
                @Expose
                private String href;
            }
        }

        public class Embedded {
            @SerializedName("premium_jobs")
            @Expose
            public List<Object> premiumJobs = null;
        }

        private class JobCountry {
            @SerializedName("country_id")
            @Expose
            private CountryId countryId;

            private class CountryId {
                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("currency_symbol")
                @Expose
                private String currencySymbol;
            }
        }
    }

    public static class JobCounts {
        @SerializedName("professions")
        @Expose
        private List<Profession> professions = null;
        @SerializedName("roles")
        @Expose
        private List<JobAdsRole> roles = null;
        @SerializedName("countries")
        @Expose
        private List<JobAdsCountry_> countries = null;
        @SerializedName("locations")
        @Expose
        private List<JobAdsLocation> locations = null;
        @SerializedName("areas")
        @Expose
        private List<JobAdsArea> areas = null;
        @SerializedName("work_types")
        @Expose
        private List<JobAdsWorkType> workTypes = null;
        @SerializedName("advertisers")
        @Expose
        private List<JobAdsAdvertiser> advertisers = null;

        private class Profession {
            @SerializedName("profession_id")
            @Expose
            private Integer professionId;
        }

        private class JobAdsRole {
            @SerializedName("role_id")
            @Expose
            private Integer roleId;
        }

        private class JobAdsCountry_ {
            @SerializedName("country_id")
            @Expose
            private Integer countryId;
        }

        private class JobAdsLocation {
            @SerializedName("location_id")
            @Expose
            private Integer locationId;
        }

        private class JobAdsArea {
            @SerializedName("area_id")
            @Expose
            private Integer areaId;
        }

        private class JobAdsWorkType {
            @SerializedName("work_type_id")
            @Expose
            private Integer workTypeId;
        }

        private class JobAdsAdvertiser {
            @SerializedName("advertiser_id")
            @Expose
            private Integer advertiserId;
        }
    }
}
