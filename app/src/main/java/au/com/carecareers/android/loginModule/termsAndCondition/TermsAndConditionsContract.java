package au.com.carecareers.android.loginModule.termsAndCondition;


import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;

/**
 * Created by Sanjay on 14/11/2017.
 */

public class TermsAndConditionsContract {
    public interface ITermsAndConditionsContractView extends IBaseView {

    }

    public interface ITermsAndConditionsContractPresenter extends IBasePresenter<ITermsAndConditionsContractView, ITermsAndConditionsContractInteractor> {
    }

    public interface ITermsAndConditionsContractInteractor extends IBaseInteractor {
    }
}
