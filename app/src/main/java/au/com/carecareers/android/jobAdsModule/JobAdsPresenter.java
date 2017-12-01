package au.com.carecareers.android.jobAdsModule;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nikesh on 12/1/2017.
 */

public class JobAdsPresenter extends BasePresenter<JobAdsContract.IJobAdsView, JobAdsContract.IJobAdsInteractor> implements JobAdsContract.IJobAdsPresenter {
    @Inject
    public JobAdsPresenter(JobAdsContract.IJobAdsInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }
}
