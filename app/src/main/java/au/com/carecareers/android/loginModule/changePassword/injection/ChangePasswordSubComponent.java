package au.com.carecareers.android.loginModule.changePassword.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.loginModule.changePassword.ChangePasswordActivity;
import dagger.Subcomponent;

/**
 * Created by Nikesh on 11/21/2017.
 */
@ActivityScope
@Subcomponent(modules = ChangePasswordModule.class)
public interface ChangePasswordSubComponent {
    void inject(ChangePasswordActivity changePasswordActivity);
}

