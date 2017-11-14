package au.com.carecareers.android.loginModule.login.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.loginModule.login.LoginActivity;
import dagger.Subcomponent;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */
@ActivityScope
@Subcomponent(modules = LoginModule.class)
public interface LoginSubComponent {
    void inject(LoginActivity loginActivity);
}
