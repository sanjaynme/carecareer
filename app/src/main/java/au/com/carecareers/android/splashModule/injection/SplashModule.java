package au.com.carecareers.android.splashModule.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.splashModule.SplashContract;
import au.com.carecareers.android.splashModule.SplashInteractor;
import au.com.carecareers.android.splashModule.SplashPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nikesh on 11/16/2017.
 */
@Module
public class SplashModule {
    public SplashModule() {

    }

    @ActivityScope
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @ActivityScope
    @Provides
    SplashContract.ISplashPresenter providePresenter(SplashPresenter splashPresenter) {
        return splashPresenter;
    }

    @ActivityScope
    @Provides
    SplashContract.ISplashInteractor provideSplashInteractor(SplashInteractor splashInteractor) {
        return splashInteractor;
    }
}
