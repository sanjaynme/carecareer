package au.com.carecareers.android.jobModule.applyJobModule.injection;

import au.com.carecareers.android.jobModule.applyJobModule.ApplyJobContract;
import au.com.carecareers.android.jobModule.applyJobModule.ApplyJobInteractor;
import au.com.carecareers.android.jobModule.applyJobModule.ApplyJobPresenter;
import au.com.carecareers.android.injection.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nikesh on 12/1/2017.
 */
@Module
public class ApplyJobsModule {
    public ApplyJobsModule() {
    }

    @Provides
    @ActivityScope
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ActivityScope
    ApplyJobContract.IApplyJobInteractor provideApplyJobInteractor(ApplyJobInteractor applyJobInteractor) {
        return applyJobInteractor;
    }

    @Provides
    @ActivityScope
    ApplyJobContract.IApplyJobPresenter provideApplyJobPresenter(ApplyJobPresenter applyJobPresenter) {
        return applyJobPresenter;
    }
}
