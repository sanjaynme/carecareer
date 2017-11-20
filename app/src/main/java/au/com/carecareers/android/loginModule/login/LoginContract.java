package au.com.carecareers.android.loginModule.login;


import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.loginModule.login.model.LoginModel;
import io.reactivex.Observable;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */

public class LoginContract {
    public interface ILoginView extends IBaseView {
        void navigateToMainActivity();
    }

    public interface ILoginPresenter extends IBasePresenter<ILoginView, ILoginInteractor> {
        void loginClicked(String username, String password);

        boolean validateFields(String username, String password);
    }

    public interface ILoginInteractor extends IBaseInteractor {
//        Observable<LoginModel.LoginRespones> login(LoginModel.LoginRequest request);

        Observable<LoginModel.LoginRespones> login(String username, String password);
    }
}
