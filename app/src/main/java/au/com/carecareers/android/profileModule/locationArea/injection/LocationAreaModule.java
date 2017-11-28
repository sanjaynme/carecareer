package au.com.carecareers.android.profileModule.locationArea.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.locationArea.LocationAreaContract;
import au.com.carecareers.android.profileModule.locationArea.LocationAreaInteractor;
import au.com.carecareers.android.profileModule.locationArea.LocationAreaPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nischal Manandhar on 28/11/2017.
 */
@Module
public class LocationAreaModule {
    public LocationAreaModule() {
    }

    @Provides
    @ActivityScope
    CompositeDisposable providecompositeDisposable() {
        return new CompositeDisposable();

    }

    @Provides
    @ActivityScope
    LocationAreaContract.ILocationAreaPresenter providePresenter(LocationAreaPresenter presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    LocationAreaContract.ILocationAreaInteractor provideInteractor(LocationAreaInteractor interactor) {
        return interactor;
    }
}
