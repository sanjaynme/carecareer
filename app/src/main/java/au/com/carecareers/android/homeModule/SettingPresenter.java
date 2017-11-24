package au.com.carecareers.android.homeModule;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.presenter.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import okhttp3.ResponseBody;

/**
 * Created by Nikesh on 11/24/2017.
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
        getInteractor().logout();
        getView().hideProgressDialog();
        getView().navigateToLandingActivity();

        /*getCompositeDisposable().add(getInteractor().logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()));*/
    }

    private DisposableCompletableObserver getObserver() {
        return new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                Log.d(TAG, "onNext: ");
                getView().hideProgressDialog();
                getView().navigateToLandingActivity();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
                ResponseBody responseBody = ((HttpException) e).response().errorBody();
                getView().hideProgressDialog();
            }
        };
    }

}
