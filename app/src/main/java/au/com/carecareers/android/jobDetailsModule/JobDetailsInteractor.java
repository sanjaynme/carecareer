package au.com.carecareers.android.jobDetailsModule;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;

/**
 * Created by Nikesh on 12/1/2017.
 */

public class JobDetailsInteractor extends BaseInteractor implements JobDetailsContract.IJobDetailsInteractor {
    @Inject
    public JobDetailsInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }
}
