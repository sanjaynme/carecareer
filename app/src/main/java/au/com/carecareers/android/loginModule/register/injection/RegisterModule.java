package au.com.carecareers.android.loginModule.register.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.loginModule.register.RegisterInteractor;
import au.com.carecareers.android.loginModule.register.RegisterPresenter;
import au.com.carecareers.android.loginModule.register.model.RegisterContract;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Dell on 11/16/2017.
 */
@Module
public class RegisterModule {
    public RegisterModule() {
    }

    @Provides
    @ActivityScope
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ActivityScope
    RegisterContract.IRegisterPresenter provideRegisterPresenter(RegisterPresenter registerPresenter) {
        return registerPresenter;
    }

    @Provides
    @ActivityScope
    RegisterContract.IRegisterInteractor provideRegisterInteractor(RegisterInteractor registerInteractor) {
        return registerInteractor;
    }
}
