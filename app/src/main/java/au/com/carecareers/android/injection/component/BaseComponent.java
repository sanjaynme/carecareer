package au.com.carecareers.android.injection.component;

import javax.inject.Singleton;

import au.com.carecareers.android.injection.module.AppModule;
import au.com.carecareers.android.injection.module.NetModule;
import au.com.carecareers.android.loginModule.login.injection.LoginSubComponent;
import au.com.carecareers.android.loginModule.login.injection.LoginModule;
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
}
