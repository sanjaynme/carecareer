package au.com.carecareers.android.loginModule.login;

import android.util.Log;
import android.util.Patterns;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.loginModule.login.model.LoginModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

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
    public void loginClicked(String username, String password) {
        getView().showProgressDialog(R.string.msg_loading);
        getCompositeDisposable().add(getInteractor().login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()));
    }

    private DisposableObserver<LoginModel.LoginRespones> getObserver() {
        return new DisposableObserver<LoginModel.LoginRespones>() {
            @Override
            public void onNext(LoginModel.LoginRespones loginRespones) {
                Log.d(TAG, "onNext: ");
                getView().hideProgressDialog();
                getView().navigateToMainActivity();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");

                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    Response response = httpException.response();
                }

                if (e instanceof RetrofitException) {

                }
                if (e instanceof IOException) {

                }
                getView().hideProgressDialog();
                getView().showError(R.string.err_);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
                getView().hideProgressDialog();
            }
        };
    }

    @Override
    public boolean validateFields(String username, String password) {
        if (username.isEmpty()) {
            getView().showAlertDialog(R.string.err_username);
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            getView().showAlertDialog(R.string.err_email_valid);
            return false;
        } else if (password.length() == 0) {
            getView().showAlertDialog(R.string.err_password);
            return false;
        } else if (password.length() < 8) {
            getView().showAlertDialog(R.string.err_password_length);
            return false;
        }
        return true;
    }
}
