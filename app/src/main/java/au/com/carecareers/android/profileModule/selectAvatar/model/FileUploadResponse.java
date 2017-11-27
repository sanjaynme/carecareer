package au.com.carecareers.android.profileModule.selectAvatar.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nischal Manandhar on 27/11/2017.
 */

public class FileUploadResponse {
    @SerializedName("name")
    public String name;
    @SerializedName("size")
    public int size;
    @SerializedName("type")
    public String type;
    @SerializedName("file")
    public String file;
    @SerializedName("extension")
    public String extension;
}
