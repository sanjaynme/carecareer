package au.com.carecareers.android.application;

import android.app.Application;

import javax.inject.Inject;

import au.com.carecareers.android.BuildConfig;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.injection.component.DaggerBaseComponent;
import au.com.carecareers.android.injection.module.AppModule;
import au.com.carecareers.android.injection.module.NetModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public class CareCareerApp extends Application {
    @Inject
    CalligraphyConfig mCalligraphyConfig;

    private BaseComponent mBaseComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mBaseComponent = DaggerBaseComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BuildConfig.BASE_URL))
                .build();

        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    public BaseComponent getBaseComponent() {
        return mBaseComponent;
    }
}
