package au.com.carecareers.android.loginModule.login;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.loginModule.login.model.LoginModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView, LoginContract.ILoginInteractor>
        implements LoginContract.ILoginPresenter {

    @Inject
    public LoginPresenter(LoginContract.ILoginInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    @Override
    public void loginClicked(LoginModel.LoginRequest loginRequest) {
        getCompositeDisposable().add(getInteractor().login(loginRequest)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginModel.LoginRespones>() {
                    @Override
                    public void accept(LoginModel.LoginRespones loginRespones) throws Exception {
                        if (isViewAttached()) {
                            getView().navigateToMainActivity();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }

    @Override
    public boolean validateFields(LoginModel.LoginRequest loginModel) {
        if (loginModel.getUsername().isEmpty()) {
            getView().showAlertDialog(R.string.err_username);
            return false;
        } else if (loginModel.getPassword().length() == 0) {
            getView().showAlertDialog(R.string.err_password);
            return false;
        } else if (loginModel.getPassword().length() < 8) {
            getView().showAlertDialog(R.string.err_password_length);
            return false;
        }
        return true;
    }
}
