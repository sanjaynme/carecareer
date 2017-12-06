package au.com.carecareers.android.jobDetailsModule;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.jobAdsModule.model.JobAdsModel;
import au.com.carecareers.android.jobDetailsModule.model.JobsDetailsModel;
import io.reactivex.Observable;

/**
 * Created by Nikesh on 12/1/2017.
 */

public class JobDetailsInteractor extends BaseInteractor implements JobDetailsContract.IJobDetailsInteractor {
    @Inject
    public JobDetailsInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public Observable<JobsDetailsModel.JobsDetailResponse> getJobDetails(int jobSearchId) {
        return getApiService().getJobDetails(getPreferenceManager().getStringValues(AppContract.Preferences.AUTHORIZATION_KEY), jobSearchId);
    }

    @Override
    public Observable<JobAdsModel.JobAdsResponse> searchJobAds(Integer searchJobAdId) {
        return getApiService().getJobAdsByAdvertiserId(getPreferenceManager().getStringValues(AppContract.Preferences.AUTHORIZATION_KEY), searchJobAdId);

    }
}
