package au.com.carecareers.android.jobModule.jobSearchModule.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nikesh on 11/29/2017.
 */

public class LocationModel {
    public static class LocationResponse {
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

        public Embedded getEmbedded() {
            return embedded;
        }

        public void setEmbedded(Embedded embedded) {
            this.embedded = embedded;
        }

        private class Links {
            @SerializedName("self")
            @Expose
            private Self self;
            @SerializedName("first")
            @Expose
            private First first;
            @SerializedName("last")
            @Expose
            private Last last;

            public Self getSelf() {
                return self;
            }

            public void setSelf(Self self) {
                this.self = self;
            }

            public First getFirst() {
                return first;
            }

            public void setFirst(First first) {
                this.first = first;
            }

            public Last getLast() {
                return last;
            }

            public void setLast(Last last) {
                this.last = last;
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

            private class First {
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

            private class Last {
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

        public static class Embedded {
            @SerializedName("locations")
            @Expose
            private List<SearchLocation> locations = null;

            public List<SearchLocation> getLocations() {
                return locations;
            }

            public void setLocations(List<SearchLocation> locations) {
                this.locations = locations;
            }

            public static class SearchLocation {
                @SerializedName("id")
                @Expose
                public Integer id;
                @SerializedName("country_id")
                @Expose
                private Integer countryId;
                @SerializedName("name")
                @Expose
                public String name;
                @SerializedName("active")
                @Expose
                private Integer active;
                @SerializedName("position")
                @Expose
                private Integer position;
                @SerializedName("_links")
                @Expose
                private Links_ links;

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

                public Links_ getLinks() {
                    return links;
                }

                public void setLinks(Links_ links) {
                    this.links = links;
                }

                private class Links_ {
                    @SerializedName("self")
                    @Expose
                    private Self_ self;

                    public Self_ getSelf() {
                        return self;
                    }

                    public void setSelf(Self_ self) {
                        this.self = self;
                    }

                    private class Self_ {
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
    }
}
