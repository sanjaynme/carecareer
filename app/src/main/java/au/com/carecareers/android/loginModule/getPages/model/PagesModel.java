package au.com.carecareers.android.loginModule.getPages.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanjay on 14/11/2017.
 */

public class PagesModel {

    public class PagesRespones {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("excerpt")
        @Expose
        private Object excerpt;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("featured")
        @Expose
        private Integer featured;
        @SerializedName("position")
        @Expose
        private Integer position;
        @SerializedName("page_title")
        @Expose
        private Object pageTitle;
        @SerializedName("meta_description")
        @Expose
        private String metaDescription;
        @SerializedName("meta_keywords")
        @Expose
        private Object metaKeywords;
        @SerializedName("_links")
        @Expose
        private Links links;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getExcerpt() {
            return excerpt;
        }

        public void setExcerpt(Object excerpt) {
            this.excerpt = excerpt;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getFeatured() {
            return featured;
        }

        public void setFeatured(Integer featured) {
            this.featured = featured;
        }

        public Integer getPosition() {
            return position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public Object getPageTitle() {
            return pageTitle;
        }

        public void setPageTitle(Object pageTitle) {
            this.pageTitle = pageTitle;
        }

        public String getMetaDescription() {
            return metaDescription;
        }

        public void setMetaDescription(String metaDescription) {
            this.metaDescription = metaDescription;
        }

        public Object getMetaKeywords() {
            return metaKeywords;
        }

        public void setMetaKeywords(Object metaKeywords) {
            this.metaKeywords = metaKeywords;
        }

        public Links getLinks() {
            return links;
        }

        public void setLinks(Links links) {
            this.links = links;
        }

        private class Links {
            @SerializedName("self")
            @Expose
            private Self self;
            @SerializedName("url")
            @Expose
            private UrlClass url;

            public Self getSelf() {
                return self;
            }

            public void setSelf(Self self) {
                this.self = self;
            }

            public UrlClass getUrl() {
                return url;
            }

            public void setUrl(UrlClass url) {
                this.url = url;
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

            private class UrlClass {
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

