package au.com.carecareers.android.loginModule.changePassword.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.loginModule.changePassword.ChangePasswordContract;
import au.com.carecareers.android.loginModule.changePassword.ChangePasswordInteractor;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nikesh on 11/21/2017.
 */
@Module
public class ChangePasswordModule {
    public ChangePasswordModule() {

    }

    @Provides
    @ActivityScope
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ActivityScope
    ChangePasswordContract.IChangeInteractor provideChangePasswordInteractor(ChangePasswordInteractor forgotPasswordInteractor) {
        return forgotPasswordInteractor;
    }
}
