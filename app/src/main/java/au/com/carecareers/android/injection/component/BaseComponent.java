package au.com.carecareers.android.injection.component;

import javax.inject.Singleton;

import au.com.carecareers.android.injection.module.AppModule;
import au.com.carecareers.android.injection.module.NetModule;
import au.com.carecareers.android.loginModule.login.injection.LoginModule;
import au.com.carecareers.android.loginModule.login.injection.LoginSubComponent;
import au.com.carecareers.android.profileModule.preferredLocation.PreferredLocationActivity;
import au.com.carecareers.android.profileModule.profileSetup.injection.ProfileSetupModule;
import au.com.carecareers.android.profileModule.profileSetup.injection.ProfileSetupSubComponent;
import dagger.Component;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        NetModule.class,
})
public interface BaseComponent {
    LoginSubComponent provideLoginSubComponent(LoginModule loginModule);

    ProfileSetupSubComponent provideProfileSetupSubComponent(ProfileSetupModule profileSetupModule);

    void inject(PreferredLocationActivity preferredLocationActivity);
}
