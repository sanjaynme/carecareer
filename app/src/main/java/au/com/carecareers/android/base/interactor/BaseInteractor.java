package au.com.carecareers.android.base.interactor;

import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.contracts.UrlContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.splashModule.model.AuthenticationModel;
import au.com.carecareers.android.utilities.AppLog;
import io.reactivex.Observable;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public abstract class BaseInteractor implements IBaseInteractor {
    private ApiService mApiService;
    private SharedPreferenceManager mSharedPreferenceManager;

    public BaseInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        this.mApiService = apiService;
        this.mSharedPreferenceManager = sharedPreferenceManager;
    }

    public ApiService getApiService() {
        return mApiService;
    }

    public SharedPreferenceManager getPreferenceManager() {
        return mSharedPreferenceManager;
    }

    @Override
    public Observable<AuthenticationModel.AuthenticationResponse> getAuthenticationToken() {
        return getApiService().auth(AuthenticationModel.getBase64(), UrlContract.Values.CLIENT_CREDENTIALS);
    }

    public void saveAuthenticationToken(AuthenticationModel.AuthenticationResponse authenticationResponse) {
        AppLog.d("authKey:::" + authenticationResponse.getTokenType() + " " + authenticationResponse.getAccessToken());
        getPreferenceManager().setKeyValues(AppContract.Preferences.AUTHORIZATION_KEY, authenticationResponse.getTokenType() + " " + authenticationResponse.getAccessToken());
    }
}
