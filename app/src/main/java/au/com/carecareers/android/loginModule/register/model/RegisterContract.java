package au.com.carecareers.android.loginModule.register.model;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import io.reactivex.Observable;

/**
 * Created by Nikesh on 11/15/2017.
 */

public class RegisterContract {
    public interface IRegisterView extends IBaseView {
        void navigateToLoginActivity(RegisterModel.RegisterResponse registerResponse);

        void setUpStatesSpinner(TaxonomyModel.TaxonomyResponse taxonomyResponse);
    }

    public interface IRegisterPresenter extends IBasePresenter<RegisterContract.IRegisterView, RegisterContract.IRegisterInteractor> {
        void getStates();

        boolean validateFields(RegisterModel.RegisterRequest registerModel);

        void sendRegisterDetails(RegisterModel.RegisterRequest registerModel);

        boolean validateSpinner(int selectedItem);
    }

    public interface IRegisterInteractor extends IBaseInteractor {
        Observable<TaxonomyModel.TaxonomyResponse> getStates();

        Observable<RegisterModel.RegisterResponse> register(RegisterModel.RegisterRequest request);

        void saveRegisterResponse(RegisterModel.RegisterResponse registerResponse);
    }
}
