package au.com.carecareers.android.profileModule.workType;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.injection.scope.ActivityScope;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nischal Manandhar on 05/12/2017.
 */
@ActivityScope
public class WorkTypePresenter extends BasePresenter<WorkTypeContract.IView, WorkTypeContract.IInteractor> implements WorkTypeContract.IPresenter {
    @Inject
    public WorkTypePresenter(WorkTypeContract.IInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }
}
