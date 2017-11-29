package au.com.carecareers.android.homeModule.injection;

import au.com.carecareers.android.homeModule.SettingContract;
import au.com.carecareers.android.homeModule.SettingInteractor;
import au.com.carecareers.android.homeModule.SettingPresenter;
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
