package au.com.carecareers.android.splashModule;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.splashModule.model.AuthenticationModel;
import io.reactivex.Observable;

/**
 * Created by Sanjay on 11/16/2017.
 */

public class SplashContract {
    public interface ISplashView extends IBaseView {
        void navigateToLandingActivity();
    }

    public interface ISplashPresenter extends IBasePresenter<SplashContract.ISplashView, SplashContract.ISplashInteractor> {
        void auth();
    }

    public interface ISplashInteractor extends IBaseInteractor {
        Observable<AuthenticationModel.AuthenticationResponse> auth();

        void saveBasicAuthToken(AuthenticationModel.AuthenticationResponse authenticationResponse);
    }
}
