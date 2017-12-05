package au.com.carecareers.android.profileModule.uploadFile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import au.com.carecareers.android.base.model.BaseListResponse;

/**
 * Created by Nischal Manandhar on 05/12/2017.
 */

public class UploadFileModel {
    public static class Request {
        @SerializedName("file_path")
        @Expose
        private String filePath;
        @SerializedName("type")
        @Expose
        private Integer type;

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
    }

    public static class Response {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("type")
        @Expose
        private Integer type;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("_links")
        @Expose
        private BaseListResponse.Links links;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BaseListResponse.Links getLinks() {
            return links;
        }

        public void setLinks(BaseListResponse.Links links) {
            this.links = links;
        }
    }


}
