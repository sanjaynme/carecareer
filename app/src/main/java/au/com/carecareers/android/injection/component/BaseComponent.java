package au.com.carecareers.android.injection.component;

import javax.inject.Singleton;

import au.com.carecareers.android.homeModule.myProfile.MyProfileFragment;
import au.com.carecareers.android.injection.module.AppModule;
import au.com.carecareers.android.injection.module.NetModule;
import au.com.carecareers.android.jobDetailsModule.injection.JobAdsDetailsModule;
import au.com.carecareers.android.jobDetailsModule.injection.JobAdsDetailsSubComponent;
import au.com.carecareers.android.jobSearchModule.injection.SearchModule;
import au.com.carecareers.android.jobSearchModule.injection.SearchSubComponent;
import au.com.carecareers.android.loginModule.changePassword.injection.ChangePasswordModule;
import au.com.carecareers.android.loginModule.changePassword.injection.ChangePasswordSubComponent;
import au.com.carecareers.android.loginModule.forgotPassword.injection.ForgotPasswordModule;
import au.com.carecareers.android.loginModule.forgotPassword.injection.ForgotPasswordSubComponent;
import au.com.carecareers.android.loginModule.getPages.injection.PagesModule;
import au.com.carecareers.android.loginModule.getPages.injection.PagesSubComponent;
import au.com.carecareers.android.loginModule.login.injection.LoginModule;
import au.com.carecareers.android.loginModule.login.injection.LoginSubComponent;
import au.com.carecareers.android.loginModule.register.injection.RegisterModule;
import au.com.carecareers.android.loginModule.register.injection.RegisterSubComponent;
import au.com.carecareers.android.profileModule.locationArea.injection.LocationAreaModule;
import au.com.carecareers.android.profileModule.locationArea.injection.LocationAreaSubComponent;
import au.com.carecareers.android.profileModule.preferredLocation.PreferredLocationActivity;
import au.com.carecareers.android.profileModule.professionRole.injection.ProfessionRoleModule;
import au.com.carecareers.android.profileModule.professionRole.injection.ProfessionRoleSubcomponent;
import au.com.carecareers.android.profileModule.profileSetup.injection.ProfileSetupModule;
import au.com.carecareers.android.profileModule.profileSetup.injection.ProfileSetupSubComponent;
import au.com.carecareers.android.profileModule.selectAvatar.injection.SelectAvatarModule;
import au.com.carecareers.android.profileModule.selectAvatar.injection.SelectAvatarSubComponent;
import au.com.carecareers.android.profileModule.uploadFile.injection.UploadFileModule;
import au.com.carecareers.android.profileModule.uploadFile.injection.UploadFileSubComponent;
import au.com.carecareers.android.profileModule.workType.injection.WorkTypeModule;
import au.com.carecareers.android.profileModule.workType.injection.WorkTypeSubComponent;
import au.com.carecareers.android.settingsModule.injection.SettingsModule;
import au.com.carecareers.android.settingsModule.injection.SettingsSubComponent;
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
    SplashSubComponent splashSubComponent(SplashModule splashModule);

    LoginSubComponent loginSubComponent(LoginModule loginModule);

    PagesSubComponent provideTermsAndConditionsSubComponent(PagesModule pagesModule);


    SearchSubComponent provideSearchSubComponent(SearchModule searchModule);

    SettingsSubComponent settingsSubComponent(SettingsModule settingsSubComponentModule);

    RegisterSubComponent registerSubComponent(RegisterModule registerModule);

    ForgotPasswordSubComponent forgotPasswordSubComponent(ForgotPasswordModule forgotPasswordModule);

    ChangePasswordSubComponent changePasswordSubComponent(ChangePasswordModule changePasswordModule);

    ProfileSetupSubComponent profileSetupSubComponent(ProfileSetupModule profileSetupModule);

    SelectAvatarSubComponent selectAvatarSubComponent(SelectAvatarModule selectAvatarModule);

    LocationAreaSubComponent locationAreaSubComponent(LocationAreaModule locationAreaModule);

    JobAdsDetailsSubComponent provideJobDetailsSubComponent(JobAdsDetailsModule jobAdsModule);

    ProfessionRoleSubcomponent provideProfessionRoleSubComponent(ProfessionRoleModule professionRoleModule);

    ProfessionRoleSubcomponent professionRoleSubComponent(ProfessionRoleModule professionRoleModule);

    UploadFileSubComponent uploadFileSubComponent(UploadFileModule uploadFileModule);

    WorkTypeSubComponent workTypeSubComponent(WorkTypeModule workTypeModule);

    void inject(PreferredLocationActivity preferredLocationActivity);

    void inject(MyProfileFragment myProfileFragment);

}
