package au.com.carecareers.android.profileModule.selectAvatar.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.selectAvatar.SelectAvatarContract;
import au.com.carecareers.android.profileModule.selectAvatar.SelectAvatarInteractor;
import au.com.carecareers.android.profileModule.selectAvatar.SelectAvatarPresenter;
import au.com.carecareers.android.profileModule.selectAvatar.adapter.SelectAvatarAdapter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nischal Manandhar on 23/11/2017.
 */
@Module
public class SelectAvatarModule {
    public SelectAvatarModule() {

    }

    @ActivityScope
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @ActivityScope
    @Provides
    SelectAvatarContract.ISelectAvatarInteractor provideInteractor(SelectAvatarInteractor interactor) {
        return interactor;
    }

    @ActivityScope
    @Provides
    SelectAvatarContract.ISelectAvatarPresenter providePresenter(SelectAvatarPresenter presenter) {
        return presenter;
    }
}
