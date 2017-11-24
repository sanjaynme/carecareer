package au.com.carecareers.android.homeModule;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;

/**
 * Created by Nikesh on 11/24/2017.
 */

public class SettingInteractor extends BaseInteractor implements SettingContract.ISettingInteractor {
    @Inject
    public SettingInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public void logout() {
        getPreferenceManager().setKeyValues(AppContract.Preferences.IS_LOGGED_IN, false);
//        getPreferenceManager().setKeyValues(AppContract.Preferences.AUTHORIZATION_KEY, "");
    }
}
