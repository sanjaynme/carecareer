package au.com.carecareers.android.profileModule.selectAvatar.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nischal Manandhar on 24/11/2017.
 */

public class AvatarRequest {
    @SerializedName("avatar")
    public String avatar;
    @SerializedName("file")
    public String file;
}
