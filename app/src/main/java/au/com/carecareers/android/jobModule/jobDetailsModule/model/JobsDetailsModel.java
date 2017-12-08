package au.com.carecareers.android.jobModule.jobDetailsModule.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sanjay on 12/1/2017.
 */

public class JobsDetailsModel {
    public static class JobsDetailResponse {
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("slug")
        @Expose
        public String slug;
        @SerializedName("excerpt")
        @Expose
        public String excerpt;
        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("reference_number")
        @Expose
        public String referenceNumber;
        @SerializedName("scheduled")
        @Expose
        public Integer scheduled;
        @SerializedName("posted_date")
        @Expose
        public String postedDate;
        @SerializedName("expiry_date")
        @Expose
        public String expiryDate;
        @SerializedName("address")
        @Expose
        public JobAddress address;
        @SerializedName("salary")
        @Expose
        public Salary salary;
        @SerializedName("bullets")
        @Expose
        public List<String> bullets = null;
        @SerializedName("requirements")
        @Expose
        public List<Object> requirements = null;
        @SerializedName("apply")
        @Expose
        public JobApply apply;
        @SerializedName("_links")
        @Expose
        public Links links;
        @SerializedName("_embedded")
        @Expose
        public JobEmbedded embedded;

        public class JobAddress {
            @SerializedName("formatted")
            @Expose
            public String formatted;
            @SerializedName("address")
            @Expose
            public Object address;
            @SerializedName("city")
            @Expose
            public String city;
            @SerializedName("state")
            @Expose
            public String state;
            @SerializedName("country")
            @Expose
            public String country;
            @SerializedName("postcode")
            @Expose
            public String postcode;
            @SerializedName("latitude")
            @Expose
            public Object latitude;
            @SerializedName("longitude")
            @Expose
            public Object longitude;
            @SerializedName("map_zoom")
            @Expose
            public Integer mapZoom;
        }

        public class Salary {
            @SerializedName("type")
            @Expose
            public Integer type;
            @SerializedName("min")
            @Expose
            public Integer min;
            @SerializedName("max")
            @Expose
            public Integer max;
            @SerializedName("display")
            @Expose
            public Integer display;
        }

        public class JobApply {
            @SerializedName("external")
            @Expose
            public Integer external;
            @SerializedName("capture_email")
            @Expose
            public Integer captureEmail;
            @SerializedName("screening_questions")
            @Expose
            public List<Object> screeningQuestions = null;
        }

        public class Links {
            @SerializedName("self")
            @Expose
            public Self self;
            @SerializedName("url")
            @Expose
            public JobUrl url;

            public class Self {
                @SerializedName("href")
                @Expose
                public String href;
            }

            public class JobUrl {
                @SerializedName("href")
                @Expose
                public String href;
            }
        }

        public class JobEmbedded {
            @SerializedName("advertiser")
            @Expose
            public Advertiser advertiser;
            @SerializedName("profession")
            @Expose
            public Profession profession;
            @SerializedName("role")
            @Expose
            public Role role;
            @SerializedName("country")
            @Expose
            public Country country;
            @SerializedName("location")
            @Expose
            public JobLocation location;
            @SerializedName("area")
            @Expose
            public Area area;
            @SerializedName("work_type")
            @Expose
            public WorkType workType;
            @SerializedName("sector")
            @Expose
            public Sector sector;

            public class Advertiser {
                @SerializedName("id")
                @Expose
                public Integer id;
                @SerializedName("name")
                @Expose
                public String name;
                @SerializedName("active")
                @Expose
                public Integer active;
                @SerializedName("_links")
                @Expose
                public Links_ links;

                public class Links_ {
                    @SerializedName("self")
                    @Expose
                    public Self_ self;
                    @SerializedName("logo_url")
                    @Expose
                    public LogoUrl logoUrl;

                    public class Self_ {
                        @SerializedName("href")
                        @Expose
                        public String href;
                    }

                    public class LogoUrl {
                        @SerializedName("href")
                        @Expose
                        public String href;
                    }
                }
            }

            public class Profession {

                @SerializedName("id")
                @Expose
                public Integer id;
                @SerializedName("name")
                @Expose
                public String name;
                @SerializedName("slug")
                @Expose
                public String slug;
                @SerializedName("active")
                @Expose
                public Integer active;
                @SerializedName("position")
                @Expose
                public Integer position;
                @SerializedName("_links")
                @Expose
                public Links__ links;

                public class Links__ {
                    @SerializedName("self")
                    @Expose
                    public Self__ self;

                    public class Self__ {
                        @SerializedName("href")
                        @Expose
                        public String href;
                    }
                }
            }

            public class Role {

                @SerializedName("id")
                @Expose
                public Integer id;
                @SerializedName("profession_id")
                @Expose
                public Integer professionId;
                @SerializedName("name")
                @Expose
                public String name;
                @SerializedName("slug")
                @Expose
                public String slug;
                @SerializedName("active")
                @Expose
                public Integer active;
                @SerializedName("position")
                @Expose
                public Integer position;
                @SerializedName("_links")
                @Expose
                public Links___ links;

                public class Links___ {
                    @SerializedName("self")
                    @Expose
                    public Self___ self;

                    public class Self___ {
                        @SerializedName("href")
                        @Expose
                        public String href;
                    }
                }
            }

            public class Country {
                @SerializedName("id")
                @Expose
                public Integer id;
                @SerializedName("name")
                @Expose
                public String name;
                @SerializedName("short_name")
                @Expose
                public Object shortName;
                @SerializedName("_links")
                @Expose
                public Links____ links;

                public class Links____ {
                    @SerializedName("self")
                    @Expose
                    public Self____ self;

                    public class Self____ {
                        @SerializedName("href")
                        @Expose
                        public String href;
                    }
                }
            }

            public class JobLocation {

                @SerializedName("id")
                @Expose
                public Integer id;
                @SerializedName("country_id")
                @Expose
                public Integer countryId;
                @SerializedName("name")
                @Expose
                public String name;
                @SerializedName("active")
                @Expose
                public Integer active;
                @SerializedName("position")
                @Expose
                public Integer position;
                @SerializedName("_links")
                @Expose
                public Links_____ links;

                public class Links_____ {
                    @SerializedName("self")
                    @Expose
                    public Self_____ self;

                    public class Self_____ {
                        @SerializedName("href")
                        @Expose
                        public String href;
                    }
                }
            }

            public class Area {
                @SerializedName("id")
                @Expose
                public Integer id;
                @SerializedName("location_id")
                @Expose
                public Integer locationId;
                @SerializedName("name")
                @Expose
                public String name;
                @SerializedName("active")
                @Expose
                public Integer active;
                @SerializedName("position")
                @Expose
                public Integer position;
                @SerializedName("_links")
                @Expose
                public Links______ links;

                public class Links______ {
                    @SerializedName("self")
                    @Expose
                    public Self______ self;

                    public class Self______ {
                        @SerializedName("href")
                        @Expose
                        public String href;
                    }
                }
            }

            public class WorkType {
                @SerializedName("id")
                @Expose
                public Integer id;
                @SerializedName("name")
                @Expose
                public String name;
                @SerializedName("hourly_salary")
                @Expose
                public Integer hourlySalary;
                @SerializedName("work_availability")
                @Expose
                public Integer workAvailability;
                @SerializedName("active")
                @Expose
                public Integer active;
                @SerializedName("position")
                @Expose
                public Integer position;
                @SerializedName("_links")
                @Expose
                public Links_______ links;

                public class Links_______ {

                    @SerializedName("self")
                    @Expose
                    public Self_______ self;

                    public class Self_______ {
                        @SerializedName("href")
                        @Expose
                        public String href;
                    }
                }
            }

            public class Sector {
                @SerializedName("id")
                @Expose
                public Integer id;
                @SerializedName("name")
                @Expose
                public String name;
                @SerializedName("active")
                @Expose
                public Integer active;
                @SerializedName("position")
                @Expose
                public Integer position;
                @SerializedName("_links")
                @Expose
                public Links________ links;

                public class Links________ {
                    @SerializedName("self")
                    @Expose
                    public Self________ self;

                    public class Self________ {
                        @SerializedName("href")
                        @Expose
                        public String href;
                    }
                }
            }
        }
    }
}
