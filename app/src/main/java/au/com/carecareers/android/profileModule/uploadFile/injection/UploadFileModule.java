package au.com.carecareers.android.profileModule.uploadFile.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.uploadFile.UploadFileContract;
import au.com.carecareers.android.profileModule.uploadFile.UploadFilePresenter;
import au.com.carecareers.android.profileModule.uploadFile.UploadFileInteractor;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nischal Manandhar on 04/12/2017.
 */
@Module
public class UploadFileModule {
    public UploadFileModule() {
    }

    @Provides
    @ActivityScope
    CompositeDisposable compositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ActivityScope
    UploadFileContract.IUploadFileInteractor provideInteractor(UploadFileInteractor interactor) {
        return interactor;
    }

    @Provides
    @ActivityScope
    UploadFileContract.IUploadFilePresenter providePresenter(UploadFilePresenter presenter) {
        return presenter;
    }
}
