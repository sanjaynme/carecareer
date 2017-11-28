package au.com.carecareers.android.injection.module;

import android.net.Proxy;
import android.support.annotation.Nullable;

import java.io.IOException;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.homeModule.model.TokenRefreshModel;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by Nikesh on 11/28/2017.
 */

public class TokenAuthenticator extends BaseInteractor implements Authenticator {

    private boolean refreshResult;

    public TokenAuthenticator(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }


    @Nullable
    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        refreshResult = refreshToken();
        if (refreshResult) {
            //refresh is successful
            String newaccess = "your new access token";

            // make current request with new access token
            return response.request().newBuilder()
                    .header("Authorization", newaccess)
                    .build();

        } else {
            // refresh failed , maybe you can logout user
            // returning null is critical here , because if you do not return null
            // it will try to refresh token continuously like 1000 times.
            // also you can try 2-3-4 times by depending you before logging out your user
            return null;
        }
    }

    public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
        return null;
    }

    private boolean refreshToken() {
        TokenRefreshModel.TokenRefreshRequest tokenRefreshRequest = new TokenRefreshModel.TokenRefreshRequest();
        tokenRefreshRequest.setRefreshToken(getPreferenceManager().getStringValues(AppContract.Preferences.REFRESH_TOKEN));
        tokenRefreshRequest.setClientId(AppContract.ClientCredentials.CLIENT_ID);
        tokenRefreshRequest.setClientSecret(AppContract.ClientCredentials.CLIENT_SECRET);
        getApiService().refreshToken(getPreferenceManager().getStringValues(AppContract.Preferences.AUTHORIZATION_KEY), tokenRefreshRequest);
        return true;
    }

}
