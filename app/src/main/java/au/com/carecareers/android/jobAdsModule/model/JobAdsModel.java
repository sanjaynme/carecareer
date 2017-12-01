package au.com.carecareers.android.jobAdsModule.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import au.com.carecareers.android.jobDetailsModule.model.JobsDetailsModel;

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
        private List<JobsDetailsModel.JobsDetailResponse.JobEmbedded.Country> countries = null;
        @SerializedName("job_counts")
        @Expose
        private JobCounts jobCounts;

        public class JobAdsTypes {
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

        public class Links {
            @SerializedName("self")
            @Expose
            public JobAdsSelf self;
            @SerializedName("first")
            @Expose
            public First first;
            @SerializedName("last")
            @Expose
            public Last last;

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
        }

        public class Embedded {
            @SerializedName("premium_jobs")
            @Expose
            public List<Object> premiumJobs = null;
        }
    }

    public static class JobCounts {
        @SerializedName("professions")
        @Expose
        public List<Object> professions = null;
        @SerializedName("roles")
        @Expose
        public List<Object> roles = null;
        @SerializedName("countries")
        @Expose
        public List<Object> countries = null;
        @SerializedName("locations")
        @Expose
        public List<Object> locations = null;
        @SerializedName("areas")
        @Expose
        public List<Object> areas = null;
        @SerializedName("worktypes")
        @Expose
        public List<Object> worktypes = null;
        @SerializedName("advertisers")
        @Expose
        public List<Object> advertisers = null;
    }
}
