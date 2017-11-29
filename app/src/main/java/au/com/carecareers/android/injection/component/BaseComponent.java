package au.com.carecareers.android.injection.component;

import javax.inject.Singleton;

import au.com.carecareers.android.homeModule.injection.SettingsModule;
import au.com.carecareers.android.homeModule.injection.SettingsSubComponent;
import au.com.carecareers.android.injection.module.AppModule;
import au.com.carecareers.android.injection.module.NetModule;
import au.com.carecareers.android.jobSearchModule.SearchModule;
import au.com.carecareers.android.loginModule.changePassword.injection.ChangePasswordModule;
import au.com.carecareers.android.loginModule.changePassword.injection.ChangePasswordSubComponent;
import au.com.carecareers.android.loginModule.forgotPassword.injection.ForgotPasswordModule;
import au.com.carecareers.android.loginModule.forgotPassword.injection.ForgotPasswordSubComponent;
import au.com.carecareers.android.loginModule.login.injection.LoginModule;
import au.com.carecareers.android.loginModule.login.injection.LoginSubComponent;
import au.com.carecareers.android.loginModule.register.injection.RegisterModule;
import au.com.carecareers.android.loginModule.register.injection.RegisterSubComponent;
import au.com.carecareers.android.loginModule.termsAndCondition.injection.TermsAndConditionSubComponent;
import au.com.carecareers.android.loginModule.termsAndCondition.injection.TermsAndConditionsModule;
import au.com.carecareers.android.profileModule.preferredLocation.PreferredLocationActivity;
import au.com.carecareers.android.profileModule.profileSetup.injection.ProfileSetupModule;
import au.com.carecareers.android.profileModule.profileSetup.injection.ProfileSetupSubComponent;
import au.com.carecareers.android.profileModule.selectAvatar.injection.SelectAvatarModule;
import au.com.carecareers.android.profileModule.selectAvatar.injection.SelectAvatarSubComponent;
import au.com.carecareers.android.splashModule.injection.SplashModule;
import au.com.carecareers.android.splashModule.injection.SplashSubComponent;
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
    SplashSubComponent provideSplashSubComponent(SplashModule splashModule);

    LoginSubComponent provideLoginSubComponent(LoginModule loginModule);

    TermsAndConditionSubComponent provideTermsAndConditionsSubComponent(TermsAndConditionsModule termsAndConditionsModule);

    SettingsSubComponent provideSettingsSubComponent(SettingsModule settingsModule);

    SearchSubComponent provideSearchSubComponent(SearchModule searchModule);

    RegisterSubComponent provideRegisterSubComponent(RegisterModule registerModule);

    ForgotPasswordSubComponent provideForgotPasswordSubComponent(ForgotPasswordModule forgotPasswordModule);

    ChangePasswordSubComponent provideChangePasswordSubComponent(ChangePasswordModule changePasswordModule);

    ProfileSetupSubComponent provideProfileSetupSubComponent(ProfileSetupModule profileSetupModule);

    SelectAvatarSubComponent provideSelectAvatarSubComponent(SelectAvatarModule selectAvatarModule);

    void inject(PreferredLocationActivity preferredLocationActivity);
}
