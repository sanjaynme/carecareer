package au.com.carecareers.android.loginModule.forgotPassword.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.loginModule.forgotPassword.ForgotPasswordActivity;
import dagger.Subcomponent;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */
@ActivityScope
@Subcomponent(modules = ForgotPasswordModule.class)
public interface ForgotPasswordSubComponent {
    void inject(ForgotPasswordActivity forgotPasswordActivity);
}
