package au.com.carecareers.android.loginModule.changePassword;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.loginModule.changePassword.model.ChangePasswordModel;
import io.reactivex.Observable;

/**
 * Created by Nikesh on 11/21/2017.
 */

public class ChangePasswordInteractor extends BaseInteractor implements ChangePasswordContract.IChangeInteractor {
    @Inject
    public ChangePasswordInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public Observable<ChangePasswordModel.ChangePasswordResponse> forgotPassword(String email) {
        return null;
    }
}
