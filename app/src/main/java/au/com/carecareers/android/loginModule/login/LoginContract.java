package au.com.carecareers.android.loginModule.login;


import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.loginModule.login.model.LoginRequest;
import au.com.carecareers.android.loginModule.login.model.LoginResponse;
import io.reactivex.Observable;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */

public class LoginContract {
    public interface ILoginView extends IBaseView {
        void navigateToMainActivity();
    }

    public interface ILoginPresenter extends IBasePresenter<ILoginView, ILoginInteractor> {
        void loginClicked(LoginRequest loginRequest);
    }

    public interface ILoginInteractor extends IBaseInteractor {
        Observable<LoginResponse> login(LoginRequest request);
    }
}
