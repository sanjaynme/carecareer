package au.com.carecareers.android.homeModule;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.homeModule.model.LogOutModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Sanjay on 11/24/2017.
 */

public class SettingPresenter extends BasePresenter<SettingContract.ISettingView, SettingContract.ISettingInteractor>
        implements SettingContract.ISettingPresenter {

    @Inject
    public SettingPresenter(SettingContract.ISettingInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }


    @Override
    public void onLogout() {
        getView().showProgressDialog(R.string.msg_loading);
        getCompositeDisposable().add(getInteractor().logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()));
    }

    private DisposableObserver<LogOutModel.LogOutResponse> getObserver() {
        return new DisposableObserver<LogOutModel.LogOutResponse>() {
            @Override
            public void onNext(LogOutModel.LogOutResponse logOutResponse) {
                Log.d(TAG, "onNext: ");
                getInteractor().logOutResponse(logOutResponse);
                getView().hideProgressDialog();
                getView().navigateToLandingActivity();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
                ResponseBody responseBody = ((HttpException) e).response().errorBody();
                getView().hideProgressDialog();
            }

            @Override
            public void onComplete() {
                getView().hideProgressDialog();
            }
        };
    }
}
