package au.com.carecareers.android.jobDetailsModule;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nikesh on 12/1/2017.
 */

public class JobDetailsPresenter extends BasePresenter<JobDetailsContract.IJobDetailsView, JobDetailsContract.IJobDetailsInteractor> implements JobDetailsContract.IJobDetailsPresenter {

    @Inject
    public JobDetailsPresenter(JobDetailsContract.IJobDetailsInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }
}
