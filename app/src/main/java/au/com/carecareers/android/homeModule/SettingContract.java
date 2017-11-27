package au.com.carecareers.android.homeModule;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.homeModule.model.LogOutModel;
import io.reactivex.Observable;

/**
 * Created by Sanjay on 11/24/2017.
 */

public class SettingContract {
    public interface ISettingView extends IBaseView {
        void navigateToLandingActivity();
    }

    public interface ISettingPresenter extends IBasePresenter<SettingContract.ISettingView, SettingContract.ISettingInteractor> {

        void onLogout();
    }

    public interface ISettingInteractor extends IBaseInteractor {
        Observable<LogOutModel.LogOutResponse> logout();

        void logOutResponse(LogOutModel.LogOutResponse logOutResponse);
    }
}

