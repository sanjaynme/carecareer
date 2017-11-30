package au.com.carecareers.android.settingsModule;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.homeModule.model.LogOutModel;
import au.com.carecareers.android.homeModule.model.TokenRefreshModel;
import au.com.carecareers.android.utilities.AppLog;
import io.reactivex.Observable;

/**
 * Created by Sanjay on 11/24/2017.
 */

public class SettingInteractor extends BaseInteractor implements SettingContract.ISettingInteractor {
    @Inject
    public SettingInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public Observable<LogOutModel.LogOutResponse> logout() {
        LogOutModel.LogOutRequest logOutRequest = new LogOutModel.LogOutRequest();
        logOutRequest.setToken(getPreferenceManager().getStringValues(AppContract.Preferences.ACCESS_TOKEN));
        logOutRequest.setTokenTypeHint("access_token");
        return getApiService().logout(getPreferenceManager().getStringValues(AppContract.Preferences.AUTHORIZATION_KEY), logOutRequest);
    }

    @Override
    public void logOutResponse(LogOutModel.LogOutResponse logOutResponse) {
        AppLog.d("Logout Successfull");
        getPreferenceManager().setKeyValues(AppContract.Preferences.IS_LOGGED_IN, false);
//        getPreferenceManager().setKeyValues(AppContract.Preferences.AUTHORIZATION_KEY, "");
//        getPreferenceManager().setKeyValues(AppContract.Preferences.ACCESS_TOKEN, "");
    }

    @Override
    public Observable<TokenRefreshModel.TokenRefreshResponse> refreshToken() {
        TokenRefreshModel.TokenRefreshRequest tokenRefreshRequest = new TokenRefreshModel.TokenRefreshRequest();
        tokenRefreshRequest.setRefreshToken(getPreferenceManager().getStringValues(AppContract.Preferences.REFRESH_TOKEN));
        tokenRefreshRequest.setClientId(AppContract.ClientCredentials.CLIENT_ID);
        tokenRefreshRequest.setClientSecret(AppContract.ClientCredentials.CLIENT_SECRET);
        return getApiService().refreshToken(getPreferenceManager().getStringValues(AppContract.Preferences.AUTHORIZATION_KEY), tokenRefreshRequest);
    }

/*
    public class RetryWithSessionRefresh implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest = chain.request();

            //when saving expire time :
            int expiresIn = Integer.parseInt(getPreferenceManager().getStringValues(AppContract.Preferences.EXPIRES_IN));
            Calendar c = Calendar.getInstance();
            c.add(Calendar.SECOND, expiresIn);
            getPreferenceManager().setKeyValues("expiretime", c.getTimeInMillis());

            //get expire time from shared preferences
            long expireTime = getPreferenceManager().getLongValues("expiretime");
            Calendar calendar = Calendar.getInstance();
            Date nowDate = calendar.getTime();
            calendar.setTimeInMillis(expireTime);
            Date expireDate = calendar.getTime();

            int result = nowDate.compareTo(expireDate);

            if (result == -1) {
                //refresh token here , and got new access token
                String newaccessToken = "newaccess";
                newRequest = chain.request().newBuilder()
                        .header("Authorization", newaccessToken)
                        .build();
            }
            return chain.proceed(newRequest);
        }
    }*/
}

