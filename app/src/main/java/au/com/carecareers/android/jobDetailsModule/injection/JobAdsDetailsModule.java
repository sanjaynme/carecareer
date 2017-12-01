package au.com.carecareers.android.jobDetailsModule.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.jobDetailsModule.JobDetailsContract;
import au.com.carecareers.android.jobDetailsModule.JobDetailsInteractor;
import au.com.carecareers.android.jobDetailsModule.JobDetailsPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nikesh on 12/1/2017.
 */
@Module
public class JobAdsDetailsModule {
    public JobAdsDetailsModule() {
    }

    @Provides
    @ActivityScope
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ActivityScope
    JobDetailsContract.IJobDetailsInteractor provideJobDetailsInteractor(JobDetailsInteractor jobDetailsInteractor) {
        return jobDetailsInteractor;
    }

    @Provides
    @ActivityScope
    JobDetailsContract.IJobDetailsPresenter provideJobDetailsPresenter(JobDetailsPresenter loginPresenter) {
        return loginPresenter;
    }
}
