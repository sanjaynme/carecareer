package au.com.carecareers.android.loginModule.changePassword;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.loginModule.changePassword.model.ChangePasswordModel;
import io.reactivex.Observable;

/**
 * Created by Nikesh on 11/21/2017.
 */

public class ChangePasswordContract {
    public interface IChangePasswordView extends IBaseView {

    }

    public interface IChangePresenter extends IBasePresenter<IChangePasswordView, IChangeInteractor> {

        void forgotPassword(String forgotEmail);
    }

    public interface IChangeInteractor extends IBaseInteractor {
        Observable<ChangePasswordModel.ChangePasswordResponse> forgotPassword(String email);
    }
}
