package au.com.carecareers.android.splashModule;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.contracts.UrlContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.splashModule.model.AuthenticationModel;
import io.reactivex.Observable;

/**
 * Created by Nikesh on 11/16/2017.
 */

public class SplashInteractor extends BaseInteractor implements SplashContract.ISplashInteractor {

    @Inject
    SplashInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public Observable<AuthenticationModel.AuthenticationResponse> auth(AuthenticationModel request) {
        return getApiService().auth(AuthenticationModel.getBase64(), UrlContract.Values.CLIENT_CREDENTIALS);
    }

    @Override
    public void saveBasicAuthToken(AuthenticationModel.AuthenticationResponse authenticationResponse) {
        getPreferenceManager().setKeyValues(AppContract.Preferences.AUTHORIZATION_KEY, authenticationResponse.getTokenType() + " " + authenticationResponse.getAccessToken());

    }
}
