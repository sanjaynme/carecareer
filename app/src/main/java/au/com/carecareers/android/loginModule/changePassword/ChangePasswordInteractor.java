package au.com.carecareers.android.loginModule.changePassword;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.loginModule.changePassword.model.ChangePasswordModel;
import io.reactivex.Completable;

/**
 * Created by Sanjay on 11/21/2017.
 */

public class ChangePasswordInteractor extends BaseInteractor implements ChangePasswordContract.IChangeInteractor {
    @Inject
    public ChangePasswordInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public Completable changePassword(ChangePasswordModel.ChangePasswordRequest changePasswordRequest) {
        String accessToken = getPreferenceManager().getStringValues(AppContract.Preferences.ACCESS_TOKEN);
        String candidateId = getPreferenceManager().getStringValues(AppContract.Preferences.CANDIDATE_ID);
        String bearerAccessToken = "Bearer " + accessToken;
        return getApiService().changePassword(bearerAccessToken, candidateId, changePasswordRequest);
    }
}
