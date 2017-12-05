package au.com.carecareers.android.profileModule.workType.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.workType.WorkTypeContract;
import au.com.carecareers.android.profileModule.workType.WorkTypeInteractor;
import au.com.carecareers.android.profileModule.workType.WorkTypePresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nischal Manandhar on 05/12/2017.
 */
@Module
public class WorkTypeModule {
    public WorkTypeModule() {

    }

    @Provides
    @ActivityScope
    CompositeDisposable compositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ActivityScope
    WorkTypeContract.IPresenter providePresenter(WorkTypePresenter presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    WorkTypeContract.IInteractor provideInteractor(WorkTypeInteractor interactor) {
        return interactor;
    }
}
