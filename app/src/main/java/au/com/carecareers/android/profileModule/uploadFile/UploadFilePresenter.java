package au.com.carecareers.android.profileModule.uploadFile;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.injection.scope.ActivityScope;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nischal Manandhar on 04/12/2017.
 */
@ActivityScope
public class UploadFilePresenter extends BasePresenter<UploadFileContract.IUploadFileView, UploadFileContract.IUploadFileInteractor> implements UploadFileContract.IUploadFilePresenter {
    @Inject
    public UploadFilePresenter(UploadFileContract.IUploadFileInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    @Override
    public void doneClicked(String filePath) {

    }
}
