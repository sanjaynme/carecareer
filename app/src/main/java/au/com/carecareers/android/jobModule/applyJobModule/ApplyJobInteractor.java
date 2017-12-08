package au.com.carecareers.android.jobModule.applyJobModule;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;

/**
 * Created by Nikesh on 12/1/2017.
 */

public class ApplyJobInteractor extends BaseInteractor implements ApplyJobContract.IApplyJobInteractor {
    @Inject
    public ApplyJobInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }
}
