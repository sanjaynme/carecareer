package au.com.carecareers.android.loginModule.changePassword;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.util.regex.Pattern;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.loginModule.changePassword.model.ChangePasswordModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Sanjay on 11/21/2017.
 */

public class ChangePasswordPresenter extends BasePresenter<ChangePasswordContract.IChangePasswordView, ChangePasswordContract.IChangeInteractor> implements
        ChangePasswordContract.IChangePresenter {
    @Inject
    public ChangePasswordPresenter(ChangePasswordContract.IChangeInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }


    @Override
    public boolean validateFields(ChangePasswordModel.ChangePasswordRequest changePasswordRequest) {
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        if (changePasswordRequest.getCurrentPassword().length() == 0) {
            getView().showAlertDialog(R.string.err_current_password);
            return false;
        } else if (changePasswordRequest.getCurrentPassword().length() < 8) {
            getView().showAlertDialog(R.string.err_password_length);
            return false;
        } else if (changePasswordRequest.getNewPassword().length() == 0) {
            getView().showAlertDialog(R.string.err_new_password);
            return false;
        } else if (changePasswordRequest.getNewPassword().length() < 8) {
            getView().showAlertDialog(R.string.err_password_length);
            return false;
        } else if (!UpperCasePatten.matcher(changePasswordRequest.getNewPassword()).find()) {
            getView().showAlertDialog(R.string.err_new_password_uppercase);
            return false;
        }
        return true;
    }

    @Override
    public void changePassword(ChangePasswordModel.ChangePasswordRequest changePasswordRequest) {
        getView().showProgressDialog(R.string.msg_loading);
        getCompositeDisposable().add(getInteractor().changePassword(changePasswordRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()));
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
                getView().showError(responseBody, AppContract.ErrorTypes.CHANGE_PASSWORD);
            }
        };
    }
}
