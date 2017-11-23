package au.com.carecareers.android.profileModule.profileSetup.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.profileSetup.ProfileSetupContract;
import au.com.carecareers.android.profileModule.profileSetup.ProfileSetupInteractor;
import au.com.carecareers.android.profileModule.profileSetup.ProfileSetupPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nischal Manandhar on 22/11/2017.
 */
@Module
public class ProfileSetupModule {
    public ProfileSetupModule() {

    }

    @ActivityScope
    @Provides
    CompositeDisposable compositeDisposable() {
        return new CompositeDisposable();
    }

    @ActivityScope
    @Provides
    ProfileSetupContract.IProfileSetupPresenter provideProfileSetupPresenter(ProfileSetupPresenter profileSetupPresenter) {
        return profileSetupPresenter;
    }

    @ActivityScope
    @Provides
    ProfileSetupContract.IProfileSetupInteractor provideProfileSetupInteractor(ProfileSetupInteractor profileSetupInteractor) {
        return profileSetupInteractor;
    }
}
