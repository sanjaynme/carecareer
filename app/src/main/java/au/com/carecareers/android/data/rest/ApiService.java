package au.com.carecareers.android.data.rest;


import javax.inject.Singleton;

import au.com.carecareers.android.contracts.UrlContract;
import au.com.carecareers.android.loginModule.forgotPassword.model.ForgotPasswordModel;
import au.com.carecareers.android.loginModule.login.model.LoginModel;
import au.com.carecareers.android.loginModule.register.model.RegisterModel;
import au.com.carecareers.android.loginModule.register.model.TaxonomyModel;
import au.com.carecareers.android.loginModule.termsAndCondition.model.TermsAndConditionsModel;
import au.com.carecareers.android.splashModule.model.AuthenticationModel;
import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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
    Observable<LoginModel.LoginRespones> login(@Header(UrlContract.Keys.AUTHORIZATION) String base64,
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
    @FormUrlEncoded
    @GET(UrlContract.PRIVACY_POLICY)
    Observable<TermsAndConditionsModel.TermsAndConditionsRespones> getTermsAndConditions(@Header(UrlContract.Keys.AUTHORIZATION) String base64,
                                                                                         @Field("type") String type,
                                                                                         @Field("id_or_slug") String idOrSlug);

}