package au.com.carecareers.android.loginModule.getPages;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.loginModule.getPages.model.PagesModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Sanjay on 14/11/2017.
 */

public class PagesPresenter extends BasePresenter<PagesContract.ITermsAndConditionsView, PagesContract.IPagesInteractor>
        implements PagesContract.IPagesPresenter {

    @Inject
    public PagesPresenter(PagesContract.IPagesInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    @Override
    public void getPages(String type, String idOrSlug) {
//        getView().showProgressDialog(R.string.msg_loading);
        getCompositeDisposable().add(getInteractor().getPages(type, idOrSlug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()));
    }

    private DisposableObserver<PagesModel.PagesRespones> getObserver() {
        return new DisposableObserver<PagesModel.PagesRespones>() {
            @Override
            public void onNext(PagesModel.PagesRespones pagesRespones) {
                Log.d(TAG, "onNext: ");
                getView().hideProgressDialog();
                getView().naviagteToTermsAndConditonsWebView(pagesRespones);
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
