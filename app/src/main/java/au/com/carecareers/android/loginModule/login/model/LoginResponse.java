package au.com.carecareers.android.loginModule.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */

public class LoginResponse {
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("msg")
    @Expose
    public String msg;
    @SerializedName("result")
    @Expose
    public Result result;

    public class Result {
        @SerializedName("mobile_verification_code")
        @Expose
        public String mobileVerificationCode;

    }

}