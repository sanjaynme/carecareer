package au.com.carecareers.android.loginModule.forgotPassword;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.loginModule.forgotPassword.model.ForgotPasswordModel;
import io.reactivex.Observable;

/**
 * Created by Nikesh on 11/21/2017.
 */

public class ForgotPasswordInteractor extends BaseInteractor implements ForgotPasswordContract.IForgotInteractor {
    @Inject
    public ForgotPasswordInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public Observable<ForgotPasswordModel.ForgotPasswordResponse> forgotPassword(String email) {
        return getApiService().forgotPassword(email);
    }
}
