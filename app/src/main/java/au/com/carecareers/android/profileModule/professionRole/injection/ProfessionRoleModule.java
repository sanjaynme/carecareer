package au.com.carecareers.android.profileModule.professionRole.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.professionRole.ProfessionRoleContract;
import au.com.carecareers.android.profileModule.professionRole.ProfessionRoleInteractor;
import au.com.carecareers.android.profileModule.professionRole.ProfessionRolePresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nischal Manandhar on 01/12/2017.
 */
@Module
public class ProfessionRoleModule {
    public ProfessionRoleModule() {

    }

    @Provides
    @ActivityScope
    CompositeDisposable providecompositeDisposable() {
        return new CompositeDisposable();

    }

    @Provides
    @ActivityScope
    ProfessionRoleContract.IProfessionRolePresenter providePresenter(ProfessionRolePresenter presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    ProfessionRoleContract.IProfessionRoleInteractor provideInteractor(ProfessionRoleInteractor interactor) {
        return interactor;
    }
}
