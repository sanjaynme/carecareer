package au.com.carecareers.android.profileModule.uploadFile;

import android.text.TextUtils;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.uploadFile.model.UploadFileModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

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
    public void doneClicked(UploadFileModel.Request request) {
        if (isValid(request)) {
            getView().showProgressDialog(R.string.msg_loading);
            getCompositeDisposable().add(
                    getInteractor().uploadFile(request)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableObserver<UploadFileModel>() {
                                @Override
                                public void onNext(UploadFileModel uploadFileModel) {
                                    if (isViewAttached()) {
                                        getView().hideProgressDialog();
                                        getView().showAlertDialogFinishActivity(R.string.msg_success);
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    if (isViewAttached()) {
                                        getView().hideProgressDialog();
                                    }
                                }

                                @Override
                                public void onComplete() {
                                    if (isViewAttached()) {
                                        getView().hideProgressDialog();
                                    }
                                }
                            })
            );
        }
    }

    private boolean isValid(UploadFileModel.Request request) {
        boolean isValid;
        if (TextUtils.isEmpty(request.getFilePath())) {
            getView().showAlertDialog(R.string.err_no_file_selected);
            isValid = false;
        } else if (request.getType() == 0) {
            getView().showAlertDialog(R.string.err_no_file_type_selected);
            isValid = false;
        } else {
            isValid = true;
        }
        return isValid;
    }
}
