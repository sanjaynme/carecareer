package au.com.carecareers.android.loginModule.forgotPassword;

import android.util.Log;
import android.util.Patterns;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.loginModule.forgotPassword.model.ForgotPasswordModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Nikesh on 11/21/2017.
 */

public class ForgotPasswordPresenter extends BasePresenter<ForgotPasswordContract.IForgotPasswordView, ForgotPasswordContract.IForgotInteractor> implements
        ForgotPasswordContract.IForgotPresenter {
    @Inject
    public ForgotPasswordPresenter(ForgotPasswordContract.IForgotInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    @Override
    public void forgotPassword(ForgotPasswordModel.ForgotPasswordRequest forgotEmail) {
        getView().showProgressDialog(R.string.msg_loading);
        getCompositeDisposable().add(getInteractor().forgotPassword(forgotEmail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()));
    }

    @Override
    public boolean validateFields(ForgotPasswordModel.ForgotPasswordRequest forgotPasswordRequest) {
        if (forgotPasswordRequest.getEmail().isEmpty()) {
            getView().showAlertDialog(R.string.err_email);
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(forgotPasswordRequest.getEmail()).matches()) {
            getView().showAlertDialog(R.string.err_email_valid);
            return false;
        }
        return true;
    }

    private DisposableCompletableObserver getObserver() {
        return new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                Log.d(TAG, "onNext: ");
                getView().hideProgressDialog();
                getView().navigateToLoginActivity();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
                ResponseBody responseBody = ((HttpException) e).response().errorBody();
                getView().hideProgressDialog();
                getView().showError(responseBody, AppContract.ErrorTypes.FORGOT_PASSWORD);
            }
        };
    }

}
