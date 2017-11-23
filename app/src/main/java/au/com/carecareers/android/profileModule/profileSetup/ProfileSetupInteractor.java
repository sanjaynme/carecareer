package au.com.carecareers.android.profileModule.profileSetup;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.injection.scope.ActivityScope;

/**
 * Created by Nischal Manandhar on 22/11/2017.
 */
@ActivityScope
public class ProfileSetupInteractor extends BaseInteractor implements ProfileSetupContract.IProfileSetupInteractor {
    @Inject
    public ProfileSetupInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }
}
