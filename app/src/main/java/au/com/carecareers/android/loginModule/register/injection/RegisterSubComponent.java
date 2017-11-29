package au.com.carecareers.android.loginModule.register.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.loginModule.register.RegisterActivity;
import dagger.Subcomponent;

/**
 * Created by Sanjay on 11/16/2017.
 */
@ActivityScope
@Subcomponent(modules = RegisterModule.class)
public interface RegisterSubComponent {
    void inject(RegisterActivity registerActivity);
}
