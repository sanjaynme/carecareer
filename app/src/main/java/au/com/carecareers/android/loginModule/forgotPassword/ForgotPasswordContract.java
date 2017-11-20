package au.com.carecareers.android.loginModule.forgotPassword;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;

/**
 * Created by Dell on 11/20/2017.
 */

public interface ForgotPasswordContract {
    public interface IForgotPasswordView extends IBaseView {
    }

    public interface IForgotPasswordPresenter extends IBasePresenter<ForgotPasswordContract.IForgotPasswordView, ForgotPasswordContract.IForgotPasswordInteractor> {
    }

    public interface IForgotPasswordInteractor extends IBaseInteractor {

    }
}
