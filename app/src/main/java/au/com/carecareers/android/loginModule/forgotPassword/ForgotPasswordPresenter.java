package au.com.carecareers.android.loginModule.forgotPassword;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.loginModule.forgotPassword.model.ForgotPasswordModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
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
    public void forgotPassword(String forgotEmail) {
        getView().showProgressDialog(R.string.msg_loading);
        getCompositeDisposable().add(getInteractor().forgotPassword(forgotEmail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()));
    }

    private DisposableObserver<ForgotPasswordModel.ForgotPasswordResponse> getObserver() {
        return new DisposableObserver<ForgotPasswordModel.ForgotPasswordResponse>() {
            @Override
            public void onNext(ForgotPasswordModel.ForgotPasswordResponse forgotPasswordResponse) {
                Log.d(TAG, "onNext: ");
                getView().hideProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
                if (e instanceof HttpException) {
                    ResponseBody responseBody = ((HttpException) e).response().errorBody();
                    getView().hideProgressDialog();
                    getView().showError(responseBody);
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
