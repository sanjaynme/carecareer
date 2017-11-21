package au.com.carecareers.android.loginModule.forgotPassword;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.loginModule.forgotPassword.model.ForgotPasswordModel;
import io.reactivex.Observable;

/**
 * Created by Nikesh on 11/21/2017.
 */

public class ForgotPasswordContract {

    public interface IForgotPasswordView extends IBaseView {

    }

    public interface IForgotPresenter extends IBasePresenter<IForgotPasswordView, IForgotInteractor> {

        void forgotPassword(String forgotEmail);
    }

    public interface IForgotInteractor extends IBaseInteractor {
        Observable<ForgotPasswordModel.ForgotPasswordResponse> forgotPassword(String email);
    }
}
