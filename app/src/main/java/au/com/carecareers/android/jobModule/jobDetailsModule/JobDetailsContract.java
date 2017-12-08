package au.com.carecareers.android.jobModule.jobDetailsModule;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.jobModule.jobAdsModule.model.JobAdsModel;
import au.com.carecareers.android.jobModule.jobDetailsModule.model.JobsDetailsModel;
import io.reactivex.Observable;

/**
 * Created by Nikesh on 12/1/2017.
 */

public class JobDetailsContract {
    public interface IJobDetailsView extends IBaseView {
        void populateJobDetails(JobsDetailsModel.JobsDetailResponse jobsDetailResponse);

        void navigateToJobAds(JobAdsModel.JobAdsResponse jobAdsResponse);
    }

    public interface IJobDetailsPresenter extends IBasePresenter<JobDetailsContract.IJobDetailsView, JobDetailsContract.IJobDetailsInteractor> {

        void getJobDetails(int jobSearchId);

        void searchJobs(Integer id);
    }

    public interface IJobDetailsInteractor extends IBaseInteractor {
        Observable<JobsDetailsModel.JobsDetailResponse> getJobDetails(int jobSearchId);

        Observable<JobAdsModel.JobAdsResponse> searchJobAds(Integer searchJobAdId);

    }
}
