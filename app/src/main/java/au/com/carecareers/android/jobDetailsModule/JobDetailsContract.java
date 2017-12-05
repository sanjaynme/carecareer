package au.com.carecareers.android.jobDetailsModule;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.jobDetailsModule.model.JobsDetailsModel;
import io.reactivex.Observable;

/**
 * Created by Nikesh on 12/1/2017.
 */

public class JobDetailsContract {
    public interface IJobDetailsView extends IBaseView {
        void populateJobDetails(JobsDetailsModel.JobsDetailResponse jobsDetailResponse);
    }

    public interface IJobDetailsPresenter extends IBasePresenter<JobDetailsContract.IJobDetailsView, JobDetailsContract.IJobDetailsInteractor> {

        void getJobDetails(int jobSearchId);
    }

    public interface IJobDetailsInteractor extends IBaseInteractor {
        Observable<JobsDetailsModel.JobsDetailResponse> getJobDetails(int jobSearchId);
    }
}
