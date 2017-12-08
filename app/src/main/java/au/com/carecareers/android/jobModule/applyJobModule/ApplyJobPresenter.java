package au.com.carecareers.android.jobModule.applyJobModule;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nikesh on 12/1/2017.
 */

public class ApplyJobPresenter extends BasePresenter<ApplyJobContract.IApplyJobView, ApplyJobContract.IApplyJobInteractor> implements ApplyJobContract.IApplyJobPresenter {

    @Inject
    public ApplyJobPresenter(ApplyJobContract.IApplyJobInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

}
