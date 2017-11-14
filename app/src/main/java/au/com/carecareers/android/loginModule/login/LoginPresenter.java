package au.com.carecareers.android.loginModule.login;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.loginModule.login.model.LoginRequest;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView, LoginContract.ILoginInteractor>
        implements LoginContract.ILoginPresenter {

    @Inject
    public LoginPresenter(LoginContract.ILoginInteractor interactor) {
        super(interactor);
    }

    @Override
    public void loginClicked(LoginRequest loginRequest) {
        getInteractor().login(loginRequest);
    }
}
