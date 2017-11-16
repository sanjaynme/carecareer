package au.com.carecareers.android.data.rest;


import javax.inject.Singleton;

import au.com.carecareers.android.contracts.UrlContract;
import au.com.carecareers.android.loginModule.login.model.LoginResponse;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */
@Singleton
public interface ApiService {
    @FormUrlEncoded
    @POST(UrlContract.LOG_IN)
    Observable<LoginResponse> authLogin(@Field(UrlContract.Keys.COUNTRY_CODE) String countryCode,
                                        @Field(UrlContract.Keys.PHONE) String phoneNumber);

    @FormUrlEncoded
    @POST(UrlContract.AUTHORIZE)
    Observable<LoginResponse> auth(@Field(UrlContract.Keys.COUNTRY_CODE) String countryCode,
                                   @Field(UrlContract.Keys.PHONE) String phoneNumber);



}
