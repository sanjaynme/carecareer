package au.com.carecareers.android.loginModule.termsAndCondition;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.loginModule.termsAndCondition.model.TermsAndConditionsModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Sanjay on 14/11/2017.
 */

public class TermsAndConditionPresenter extends BasePresenter<TermsAndConditionsContract.ITermsAndConditionsView, TermsAndConditionsContract.ITermsAndConditionsInteractor>
        implements TermsAndConditionsContract.ITermsAndConditionsPresenter {

    @Inject
    public TermsAndConditionPresenter(TermsAndConditionsContract.ITermsAndConditionsInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    @Override
    public void termsAndCondition(String type, String idOrSlug) {
        getView().showProgressDialog(R.string.msg_loading);
        getCompositeDisposable().add(getInteractor().termsAndConditions(type, idOrSlug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()));
    }

    private DisposableObserver<TermsAndConditionsModel.TermsAndConditionsRespones> getObserver() {
        return new DisposableObserver<TermsAndConditionsModel.TermsAndConditionsRespones>() {
            @Override
            public void onNext(TermsAndConditionsModel.TermsAndConditionsRespones termsAndConditionsRespones) {
                Log.d(TAG, "onNext: ");
                getView().hideProgressDialog();
                getView().naviagteToTermsAndConditonsWebView(termsAndConditionsRespones);
            }

            @Override
            public void onError(Throwable e) {
                ResponseBody responseBody = ((HttpException) e).response().errorBody();
                getView().hideProgressDialog();
                getView().showError(responseBody, AppContract.ErrorTypes.TERMS_AND_CONDITIONS);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
                getView().hideProgressDialog();
            }
        };
    }


}
