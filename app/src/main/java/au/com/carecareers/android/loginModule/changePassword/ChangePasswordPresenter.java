package au.com.carecareers.android.loginModule.changePassword;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.loginModule.changePassword.model.ChangePasswordModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Nikesh on 11/21/2017.
 */

public class ChangePasswordPresenter extends BasePresenter<ChangePasswordContract.IChangePasswordView, ChangePasswordContract.IChangeInteractor> implements
        ChangePasswordContract.IChangePresenter {
    @Inject
    public ChangePasswordPresenter(ChangePasswordContract.IChangeInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    @Override
    public void forgotPassword(String forgotEmail) {
        getView().showProgressDialog(R.string.msg_loading);
        getCompositeDisposable().add(getInteractor().forgotPassword(forgotEmail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()));
    }

    private DisposableObserver<ChangePasswordModel.ChangePasswordResponse> getObserver() {
        return new DisposableObserver<ChangePasswordModel.ChangePasswordResponse>() {
            @Override
            public void onNext(ChangePasswordModel.ChangePasswordResponse forgotPasswordResponse) {
                Log.d(TAG, "onNext: ");
                getView().hideProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");

                Log.d(TAG, "onError: ");
                if (e instanceof HttpException) {
                    ResponseBody responseBody = ((HttpException) e).response().errorBody();
                    getView().hideProgressDialog();
                    getView().showError(responseBody);
                }
                if (e instanceof IOException) {

                }
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
                getView().hideProgressDialog();
            }
        };
    }

}
