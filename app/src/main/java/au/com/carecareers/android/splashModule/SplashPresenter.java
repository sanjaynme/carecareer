package au.com.carecareers.android.splashModule;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sanjay on 11/16/2017.
 */

public class SplashPresenter extends BasePresenter<SplashContract.ISplashView, SplashContract.ISplashInteractor>
        implements SplashContract.ISplashPresenter {
    @Inject
    public SplashPresenter(SplashContract.ISplashInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    public void getAuthenticationToken() {
        getCompositeDisposable().add(getInteractor().getAuthenticationToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authenticationResponse -> {
                    getInteractor().saveAuthenticationToken(authenticationResponse);
                    if (isViewAttached()) {
                        getView().navigateToLandingActivity();
                    }
                }, throwable -> {

                }));
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
