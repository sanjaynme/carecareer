package au.com.carecareers.android.loginModule.login;

import android.util.Log;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.loginModule.login.model.LoginModel;
import au.com.carecareers.android.splashModule.model.AuthenticationModel;
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
    public Observable<LoginModel.LoginRespones> login(LoginModel.LoginRequest request) {
        Log.d(TAG, "login: ");
        return getApiService().login(AuthenticationModel.getBase64(), request);
    }
}
