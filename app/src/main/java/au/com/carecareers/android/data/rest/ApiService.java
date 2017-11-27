package au.com.carecareers.android.data.rest;


import javax.inject.Singleton;

import au.com.carecareers.android.contracts.UrlContract;
import au.com.carecareers.android.loginModule.forgotPassword.model.ForgotPasswordModel;
import au.com.carecareers.android.loginModule.login.model.LoginModel;
import au.com.carecareers.android.loginModule.register.model.RegisterModel;
import au.com.carecareers.android.loginModule.register.model.TaxonomyModel;
import au.com.carecareers.android.loginModule.termsAndCondition.model.TermsAndConditionsModel;
import au.com.carecareers.android.profileModule.selectAvatar.model.AvatarRequest;
import au.com.carecareers.android.profileModule.selectAvatar.model.AvatarResponse;
import au.com.carecareers.android.profileModule.selectAvatar.model.FileUploadResponse;
import au.com.carecareers.android.splashModule.model.AuthenticationModel;
import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */
@Singleton
public interface ApiService {

    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept:application/json, text/plain, */*",})
    @FormUrlEncoded
    @POST(UrlContract.AUTHORIZE)
    Observable<AuthenticationModel.AuthenticationResponse> auth(@Header(UrlContract.Keys.AUTHORIZATION) String base64,
                                                                @Field(UrlContract.Keys.GRANT_TYPE) String grantType);

    @Headers({"accept:application/json",})
    @GET(UrlContract.GETSTATES)
    Observable<TaxonomyModel.TaxonomyResponse> getStates(@Header(UrlContract.Keys.AUTHORIZATION) String base64);

    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Accept:application/json, text/plain, */*",})
    @FormUrlEncoded
    @POST(UrlContract.LOG_IN)
    Observable<LoginModel.LoginResponse> login(@Header(UrlContract.Keys.AUTHORIZATION) String base64,
                                               @Field(UrlContract.Keys.GRANT_TYPE) String grantType,
                                               @Field("username") String username,
                                               @Field("password") String password);

    @Headers({"Content-Type:application/json",
            "accept:application/json",})
    @POST(UrlContract.REGISTER)
    Observable<RegisterModel.RegisterResponse> register(@Header(UrlContract.Keys.AUTHORIZATION) String base64,
                                                        @Body RegisterModel.RegisterRequest registerRequest);


    @Headers({"Content-Type:application/json",
            "accept:application/json",})
    @POST(UrlContract.FORGOT_PASSWORD)
    Completable forgotPassword(@Header(UrlContract.Keys.AUTHORIZATION) String base64,
                               @Body ForgotPasswordModel.ForgotPasswordRequest forgotEmail);

    @Headers({"accept:application/json",})
    @GET(UrlContract.PRIVACY_POLICY)
    Observable<TermsAndConditionsModel.TermsAndConditionsRespones> getTermsAndConditions(@Header(UrlContract.Keys.AUTHORIZATION) String base64,
                                                                                         @Path("type") String type,
                                                                                         @Path("id_or_slug") String idOrSlug);

    @Headers(
            {"Content-Type:application/json",
                    "accept:application/json"
            })
    @GET(UrlContract.GET_AVATARS)
    Observable<AvatarResponse> getAvatars(@Header(UrlContract.Keys.AUTHORIZATION) String authorization,
                                          @Query(UrlContract.Keys.PAGE) int page);

    @Headers(
            {"Content-Type:application/json",
                    "accept:application/json"
            })
    @PUT(UrlContract.SET_AVATAR)
    Observable<AvatarResponse.Avatar> setAvatar(@Header(UrlContract.Keys.AUTHORIZATION) String authorization,
                                                @Path(UrlContract.Keys.CANDIDATE_ID) String candidateId,
                                                @Body AvatarRequest avatarRequest);

    @Headers(
            {
                    "accept:application/json"
            })
    @Multipart
    @POST(UrlContract.UPLOAD_FILE)
    Observable<FileUploadResponse> uploadFile(@Header(UrlContract.Keys.AUTHORIZATION) String authorization,
                                              @Part MultipartBody.Part file);
}