package au.com.carecareers.android.loginModule.forgotPassword.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.loginModule.forgotPassword.ForgotPasswordContract;
import au.com.carecareers.android.loginModule.forgotPassword.ForgotPasswordInteractor;
import au.com.carecareers.android.loginModule.forgotPassword.ForgotPasswordPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nikesh on 11/21/2017.
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
    ForgotPasswordContract.IForgotPresenter provideForgotPasswordPresenter(ForgotPasswordPresenter forgotPasswordPresenter) {
        return forgotPasswordPresenter;
    }

    @Provides
    @ActivityScope
    ForgotPasswordContract.IForgotInteractor provideForgotPasswordInteractor(ForgotPasswordInteractor forgotPasswordInteractor) {
        return forgotPasswordInteractor;
    }
}
