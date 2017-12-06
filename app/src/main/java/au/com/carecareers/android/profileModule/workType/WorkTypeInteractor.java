package au.com.carecareers.android.profileModule.workType;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.injection.scope.ActivityScope;

/**
 * Created by Nischal Manandhar on 05/12/2017.
 */
@ActivityScope
public class WorkTypeInteractor extends BaseInteractor implements WorkTypeContract.IInteractor {

    @Inject
    public WorkTypeInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }
}
