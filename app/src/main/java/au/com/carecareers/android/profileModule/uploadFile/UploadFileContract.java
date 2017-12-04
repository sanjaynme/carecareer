package au.com.carecareers.android.profileModule.uploadFile;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;

/**
 * Created by Nischal Manandhar on 04/12/2017.
 */

public class UploadFileContract {
    public interface IUploadFileView extends IBaseView {

    }

    public interface IUploadFilePresenter extends IBasePresenter<IUploadFileView, IUploadFileInteractor> {
        void doneClicked(String filePath);
    }

    public interface IUploadFileInteractor extends IBaseInteractor {
        void uploadFile(String filePath);
    }
}
