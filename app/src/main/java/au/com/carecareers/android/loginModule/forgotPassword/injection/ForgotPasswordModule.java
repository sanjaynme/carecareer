package au.com.carecareers.android.loginModule.forgotPassword.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.loginModule.forgotPassword.ForgotPasswordContract;
import au.com.carecareers.android.loginModule.forgotPassword.ForgotPasswordInteractor;
import au.com.carecareers.android.loginModule.forgotPassword.ForgotPasswordPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */
@Module
public class ForgotPasswordModule {
    public ForgotPasswordModule() {
    }

    @Provides
    @ActivityScope
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ActivityScope
    ForgotPasswordContract.IForgotPasswordInteractor provideForgotPasswordInteractor(ForgotPasswordInteractor forgotPasswordInteractor) {
        return forgotPasswordInteractor;
    }

    @Provides
    @ActivityScope
    ForgotPasswordContract.IForgotPasswordPresenter provideForgotPasswordPresenter
            (ForgotPasswordPresenter forgotPasswordPresenter) {
        return forgotPasswordPresenter;
    }
}
