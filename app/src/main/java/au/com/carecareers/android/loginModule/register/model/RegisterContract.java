package au.com.carecareers.android.loginModule.register.model;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import io.reactivex.Observable;

/**
 * Created by Nikesh on 11/15/2017.
 */

public interface RegisterContract {
    public interface IRegisterView extends IBaseView {
        void navigateToMainActivity();

        void showStates(TaxonomyModel.TaxonomyResponse taxonomyResponse);
    }

    public interface IRegisterPresenter extends IBasePresenter<RegisterContract.IRegisterView, RegisterContract.IRegisterInteractor> {
        void getStates(TaxonomyModel taxonomyModel);

    }

    public interface IRegisterInteractor extends IBaseInteractor {
        Observable<TaxonomyModel.TaxonomyResponse> getStates(TaxonomyModel request);
    }
}
