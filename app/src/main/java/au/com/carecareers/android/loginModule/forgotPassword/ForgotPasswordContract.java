package au.com.carecareers.android.loginModule.forgotPassword;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.loginModule.forgotPassword.model.ForgotPasswordModel;
import io.reactivex.Completable;

/**
 * Created by Sanjay on 11/21/2017.
 */

public class ForgotPasswordContract {

    public interface IForgotPasswordView extends IBaseView {
        void navigateToLoginActivity();
    }

    public interface IForgotPresenter extends IBasePresenter<IForgotPasswordView, IForgotInteractor> {
        void forgotPassword(ForgotPasswordModel.ForgotPasswordRequest forgotEmail);

        boolean validateFields(ForgotPasswordModel.ForgotPasswordRequest forgotPasswordRequest);
    }

    public interface IForgotInteractor extends IBaseInteractor {
        Completable forgotPassword(ForgotPasswordModel.ForgotPasswordRequest email);
    }
}
