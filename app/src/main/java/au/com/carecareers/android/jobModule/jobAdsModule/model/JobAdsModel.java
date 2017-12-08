package au.com.carecareers.android.jobModule.jobAdsModule.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nikesh on 12/1/2017.
 */

public class JobAdsModel {
    public static class JobAdsRequest {
        private String q;
        private ArrayList<Integer> profession;
        private ArrayList<Integer> role;
        private ArrayList<Integer> country;
        private ArrayList<Integer> location;
        private ArrayList<Integer> area;
        private ArrayList<Integer> work_type;
        private ArrayList<Integer> advertiser;
        private String salary_type;
        private Integer salary_from;
        private Integer salary_to;
        private String sort;
        public Integer page;

        public String getKeyWords() {
            return q;
        }

        public void setKeyWords(String q) {
            this.q = q;
        }

        public ArrayList<Integer> getProfession() {
            return profession;
        }

        public void setProfession(ArrayList<Integer> profession) {
            this.profession = profession;
        }

        public ArrayList<Integer> getRole() {
            return role;
        }

        public void setRole(ArrayList<Integer> role) {
            this.role = role;
        }

        public ArrayList<Integer> getCountry() {
            return country;
        }

        public void setCountry(ArrayList<Integer> country) {
            this.country = country;
        }

        public ArrayList<Integer> getLocation() {
            return location;
        }

        public void setLocation(ArrayList<Integer> location) {
            this.location = location;
        }

        public ArrayList<Integer> getArea() {
            return area;
        }

        public void setArea(ArrayList<Integer> area) {
            this.area = area;
        }

        public ArrayList<Integer> getWork_type() {
            return work_type;
        }

        public void setWork_type(ArrayList<Integer> work_type) {
            this.work_type = work_type;
        }

        public ArrayList<Integer> getAdvertiser() {
            return advertiser;
        }

        public void setAdvertiser(ArrayList<Integer> advertiser) {
            this.advertiser = advertiser;
        }

        public String getSalary_type() {
            return salary_type;
        }

        public void setSalary_type(String salary_type) {
            this.salary_type = salary_type;
        }

        public Integer getSalary_from() {
            return salary_from;
        }

        public void setSalary_from(Integer salary_from) {
            this.salary_from = salary_from;
        }

        public Integer getSalary_to() {
            return salary_to;
        }

        public void setSalary_to(Integer salary_to) {
            this.salary_to = salary_to;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }
    }

    public static class JobAdsResponse {
        @SerializedName("total_items")
        @Expose
        public Integer totalItems;
        @SerializedName("page_size")
        @Expose
        public Integer pageSize;
        @SerializedName("page_count")
        @Expose
        public Integer pageCount;
        @SerializedName("page")
        @Expose
        public Integer page;
        @SerializedName("sort")
        @Expose
        public String sort;
        @SerializedName("show_premiums")
        @Expose
        public Boolean showPremiums;
        @SerializedName("job_types")
        @Expose
        public JobTypes jobTypes;
        @SerializedName("countries")
        @Expose
        public Countries countries;
        @SerializedName("job_counts")
        @Expose
        public JobCounts jobCounts;
        @SerializedName("_links")
        @Expose
        public Links links;
        @SerializedName("_embedded")
        @Expose
        public Embedded embedded;

        public class JobTypes {

            @SerializedName("1")
            @Expose
            public String _1;
            @SerializedName("2")
            @Expose
            public String _2;
            @SerializedName("3")
            @Expose
            public String _3;
        }

        public class Countries {
            @SerializedName("1")
            @Expose
            public CountriesId _1;


            public class CountriesId {
                @SerializedName("id")
                @Expose
                public Integer id;
                @SerializedName("currency_symbol")
                @Expose
                public Object currencySymbol;
            }
        }

        public class JobCounts {
            @SerializedName("professions")
            @Expose
            public Professions professions;
            @SerializedName("roles")
            @Expose
            public Roles roles;
            @SerializedName("countries")
            @Expose
            public Countries_ countries;
            @SerializedName("locations")
            @Expose
            public Locations locations;
            @SerializedName("areas")
            @Expose
            public Areas areas;
            @SerializedName("worktypes")
            @Expose
            public Worktypes worktypes;
            @SerializedName("advertisers")
            @Expose
            public Advertisers advertisers;

            public class Professions {
                @SerializedName("11")
                @Expose
                public Integer _11;
                @SerializedName("28")
                @Expose
                public Integer _28;
                @SerializedName("29")
                @Expose
                public Integer _29;
                @SerializedName("31")
                @Expose
                public Integer _31;
            }

            public class Roles {
                @SerializedName("497")
                @Expose
                public Integer _497;
                @SerializedName("715")
                @Expose
                public Integer _715;
                @SerializedName("729")
                @Expose
                public Integer _729;
                @SerializedName("763")
                @Expose
                public Integer _763;
                @SerializedName("764")
                @Expose
                public Integer _764;
                @SerializedName("767")
                @Expose
                public Integer _767;
            }

            public class Countries_ {
                @SerializedName("1")
                @Expose
                public Integer _1;
            }

            public class Locations {

                @SerializedName("1")
                @Expose
                public Integer _1;
                @SerializedName("10")
                @Expose
                public Integer _10;
            }

            public class Areas {
                @SerializedName("18")
                @Expose
                public Integer _18;
                @SerializedName("24")
                @Expose
                public Integer _24;
                @SerializedName("89")
                @Expose
                public Integer _89;
            }

            public class Worktypes {
                @SerializedName("7")
                @Expose
                public Integer _7;
            }

            public class Advertisers {
                @SerializedName("3")
                @Expose
                public Integer _3;
            }
        }

        public class Links {
            @SerializedName("self")
            @Expose
            public Self self;
            @SerializedName("first")
            @Expose
            public First first;
            @SerializedName("last")
            @Expose
            public Last last;

            public class Self {

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
        }

        public class Embedded {
            @SerializedName("jobs")
            @Expose
            public ArrayList<Job> jobs = null;
            @SerializedName("premium_jobs")
            @Expose
            public ArrayList<Object> premiumJobs = null;

            public class Job {
                @SerializedName("id")
                @Expose
                public String id;
                @SerializedName("title")
                @Expose
                public String title;
                @SerializedName("slug")
                @Expose
                public String slug;
                @SerializedName("excerpt")
                @Expose
                public String excerpt;
                @SerializedName("type_id")
                @Expose
                public Integer typeId;
                @SerializedName("profession_name")
                @Expose
                public String professionName;
                @SerializedName("role_name")
                @Expose
                public String roleName;
                @SerializedName("country_id")
                @Expose
                public Integer countryId;
                @SerializedName("location_name")
                @Expose
                public String locationName;
                @SerializedName("area_name")
                @Expose
                public String areaName;
                @SerializedName("worktype_name")
                @Expose
                public String worktypeName;
                @SerializedName("salary_annual")
                @Expose
                public Integer salaryAnnual;
                @SerializedName("salary_from")
                @Expose
                public Integer salaryFrom;
                @SerializedName("salary_to")
                @Expose
                public Integer salaryTo;
                @SerializedName("salary_display")
                @Expose
                public Integer salaryDisplay;
                @SerializedName("posted_date")
                @Expose
                public String postedDate;
                @SerializedName("_links")
                @Expose
                public Links_ links;

                public class Links_ {

                    @SerializedName("self")
                    @Expose
                    public Self_ self;
                    @SerializedName("advertiser_logo_url")
                    @Expose
                    public AdvertiserLogoUrl advertiserLogoUrl;
                    @SerializedName("url")
                    @Expose
                    public JobAdsURL url;

                    public class Self_ {
                        @SerializedName("href")
                        @Expose
                        public String href;
                    }

                    public class AdvertiserLogoUrl {
                        @SerializedName("href")
                        @Expose
                        public String href;
                    }

                    public class JobAdsURL {
                        @SerializedName("href")
                        @Expose
                        public String href;
                    }
                }


            }
        }
    }
}
