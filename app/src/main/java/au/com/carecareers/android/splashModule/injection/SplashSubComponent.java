package au.com.carecareers.android.splashModule.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.splashModule.SplashActivity;
import dagger.Subcomponent;

/**
 * Created by Sanjay on 11/16/2017.
 */
@ActivityScope
@Subcomponent(modules = SplashModule.class)
public interface SplashSubComponent {
    void inject(SplashActivity splashActivity);
}
