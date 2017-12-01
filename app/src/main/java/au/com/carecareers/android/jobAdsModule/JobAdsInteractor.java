package au.com.carecareers.android.jobAdsModule;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;

/**
 * Created by Nikesh on 12/1/2017.
 */

public class JobAdsInteractor extends BaseInteractor implements JobAdsContract.IJobAdsInteractor {
    @Inject
    public JobAdsInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }
}
