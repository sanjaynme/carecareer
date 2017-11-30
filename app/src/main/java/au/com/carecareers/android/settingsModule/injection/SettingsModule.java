package au.com.carecareers.android.settingsModule.injection;

import au.com.carecareers.android.settingsModule.SettingContract;
import au.com.carecareers.android.settingsModule.SettingInteractor;
import au.com.carecareers.android.settingsModule.SettingPresenter;
import au.com.carecareers.android.injection.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Sanjay on 11/24/2017.
 */
@Module
public class SettingsModule {
    public SettingsModule() {
    }

    @Provides
    @ActivityScope
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ActivityScope
    SettingContract.ISettingInteractor provideSettingsInteractor(SettingInteractor settingInteractor) {
        return settingInteractor;
    }

    @Provides
    @ActivityScope
    SettingContract.ISettingPresenter provideSettingsPresenter(SettingPresenter settingPresenter) {
        return settingPresenter;
    }

}
