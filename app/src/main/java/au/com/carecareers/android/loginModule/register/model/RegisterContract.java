package au.com.carecareers.android.loginModule.register.model;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import io.reactivex.Observable;

/**
 * Created by Sanjay on 11/15/2017.
 */

public class RegisterContract {
    public interface IRegisterView extends IBaseView {
        void navigateToLoginActivity(RegisterModel.RegisterResponse registerResponse);

        void setUpStatesSpinner(TaxonomyModel.TaxonomyResponse taxonomyResponse);
    }

    public interface IRegisterPresenter extends IBasePresenter<RegisterContract.IRegisterView, RegisterContract.IRegisterInteractor> {
        void getStates();

        void getStatesWithAuthTokenCheck();

        boolean validateFields(RegisterModel.RegisterRequest registerModel);

        void sendRegisterDetails(RegisterModel.RegisterRequest registerModel);

        boolean validateSpinner(int selectedItem);
    }

    public interface IRegisterInteractor extends IBaseInteractor {
        Observable<Boolean> isPreferenceCleared();

        Observable<TaxonomyModel.TaxonomyResponse> getStates();

        Observable<RegisterModel.RegisterResponse> register(RegisterModel.RegisterRequest request);

        void saveRegisterResponse(RegisterModel.RegisterResponse registerResponse);
    }
}
