package au.com.carecareers.android.loginModule.register.model;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.loginModule.login.model.LoginRequest;
import io.reactivex.Observable;

/**
 * Created by Nikesh on 11/15/2017.
 */

public interface RegisterContract {
    public interface IRegisterView extends IBaseView {
        void navigateToMainActivity();
    }

    public interface IRegisterInteractor extends IBaseInteractor {
        Observable<RegisterResponse> login(LoginRequest request);

    }

    public interface IRegisterPresenter extends IBasePresenter<IRegisterView, IRegisterInteractor> {
        void registerClicked(RegisterRequest registerRequest);
    }
}
