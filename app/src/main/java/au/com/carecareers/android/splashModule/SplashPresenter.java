package au.com.carecareers.android.splashModule;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.splashModule.model.AuthenticationModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nikesh on 11/16/2017.
 */

public class SplashPresenter extends BasePresenter<SplashContract.ISplashView, SplashContract.ISplashInteractor>
        implements SplashContract.ISplashPresenter {
    @Inject
    public SplashPresenter(SplashContract.ISplashInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    public void auth(AuthenticationModel authenticationModel) {
        getCompositeDisposable().add(getInteractor().auth(authenticationModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AuthenticationModel.AuthenticationResponse>() {
                    @Override
                    public void accept(AuthenticationModel.AuthenticationResponse authenticationResponse) throws Exception {
                        getInteractor().saveBasicAuthToken(authenticationResponse);
                        if (isViewAttached()) {
                            getView().navigateToLandingActivity();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }

}
