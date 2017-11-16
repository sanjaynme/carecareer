package au.com.carecareers.android.loginModule.login.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.loginModule.login.LoginContract;
import au.com.carecareers.android.loginModule.login.LoginInteractor;
import au.com.carecareers.android.loginModule.login.LoginPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */
@Module
public class LoginModule {
    public LoginModule() {
    }

    @Provides
    @ActivityScope
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ActivityScope
    LoginContract.ILoginInteractor provideLoginInteractor(LoginInteractor loginInteractor) {
        return loginInteractor;
    }

    @Provides
    @ActivityScope
    LoginContract.ILoginPresenter provideLoginPresenter(LoginPresenter loginPresenter) {
        return loginPresenter;
    }
}
