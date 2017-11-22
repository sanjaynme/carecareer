package au.com.carecareers.android.loginModule.login;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.contracts.UrlContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.loginModule.login.model.LoginModel;
import au.com.carecareers.android.splashModule.model.AuthenticationModel;
import au.com.carecareers.android.utilities.AppLog;
import io.reactivex.Observable;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */

public class LoginInteractor extends BaseInteractor implements LoginContract.ILoginInteractor {
    private static final String TAG = "LoginInteractor";

    @Inject
    public LoginInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public Observable<LoginModel.LoginRespones> login(String username, String password) {
        return getApiService().login(AuthenticationModel.getBase64(), UrlContract.Values.PASSWORD, username, password);
    }

    @Override
    public void saveLoginResponse(LoginModel.LoginRespones loginRespones) {
        AppLog.d("save login preferences:");
        getPreferenceManager().setKeyValues(AppContract.Preferences.ACCESS_TOKEN, loginRespones.getAccessToken());
        getPreferenceManager().setKeyValues(AppContract.Preferences.EXPIRES_IN, loginRespones.getExpiresIn());
        getPreferenceManager().setKeyValues(AppContract.Preferences.REFRESH_TOKEN, loginRespones.getRefreshToken());
        getPreferenceManager().setKeyValues(AppContract.Preferences.TOKEN_TYPE, loginRespones.getTokenType());
        getPreferenceManager().setKeyValues(AppContract.Preferences.SCOPE, loginRespones.getTokenType());
        getPreferenceManager().setKeyValues(AppContract.Preferences.CANDIDATE_ID, loginRespones.getUser().getId());
        getPreferenceManager().setKeyValues(AppContract.Preferences.FIRST_NAME, loginRespones.getUser().getFirstName());
        getPreferenceManager().setKeyValues(AppContract.Preferences.LAST_NAME, loginRespones.getUser().getLastName());
        getPreferenceManager().setKeyValues(AppContract.Preferences.LAST_LOGIN_DATE, loginRespones.getUser().getLastLoginDate());
    }
}
