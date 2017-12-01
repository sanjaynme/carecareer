package au.com.carecareers.android.jobAdsModule.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.jobAdsModule.JobAdsContract;
import au.com.carecareers.android.jobAdsModule.JobAdsInteractor;
import au.com.carecareers.android.jobAdsModule.JobAdsPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nikesh on 12/1/2017.
 */
@Module
public class JobAdsModule {
    public JobAdsModule() {
    }

    @Provides
    @ActivityScope
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ActivityScope
    JobAdsContract.IJobAdsInteractor provideJobInteractor(JobAdsInteractor jobAdsInteractor) {
        return jobAdsInteractor;
    }

    @Provides
    @ActivityScope
    JobAdsContract.IJobAdsPresenter provideJobPresenter(JobAdsPresenter jobAdsPresenter) {
        return jobAdsPresenter;
    }
}
