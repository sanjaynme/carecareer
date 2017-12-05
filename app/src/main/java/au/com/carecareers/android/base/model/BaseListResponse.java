package au.com.carecareers.android.base.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nischal Manandhar on 01/12/2017.
 */

public class BaseListResponse {
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

    public static class Links {
        @SerializedName("self")
        @Expose
        private UrlLink self;
        @SerializedName("first")
        @Expose
        private UrlLink first;
        @SerializedName("last")
        @Expose
        private UrlLink last;
        @SerializedName("next")
        @Expose
        private UrlLink next;
        @SerializedName("url")
        @Expose
        private UrlLink url;  // Link to avatar image in avatar list
        @SerializedName("avatar_url")
        @Expose
        private UrlLink avatarUrl;  // Link to saved avatar image
        @SerializedName("download_file")
        @Expose
        private UrlLink fileUrl;  // Link to saved file url

        public UrlLink getSelf() {
            return self;
        }

        public void setSelf(UrlLink self) {
            this.self = self;
        }

        public UrlLink getFirst() {
            return first;
        }

        public void setFirst(UrlLink first) {
            this.first = first;
        }

        public UrlLink getLast() {
            return last;
        }

        public void setLast(UrlLink last) {
            this.last = last;
        }

        public UrlLink getNext() {
            return next;
        }

        public void setNext(UrlLink next) {
            this.next = next;
        }

        public UrlLink getUrl() {
            return url;
        }

        public void setUrl(UrlLink url) {
            this.url = url;
        }

        public UrlLink getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(UrlLink avatarUrl) {
            this.avatarUrl = avatarUrl;
        }
    }

    public static class UrlLink {

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
