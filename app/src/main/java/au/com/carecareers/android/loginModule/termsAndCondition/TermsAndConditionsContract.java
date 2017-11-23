package au.com.carecareers.android.loginModule.termsAndCondition;


import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.loginModule.termsAndCondition.model.TermsAndConditionsModel;
import io.reactivex.Observable;

/**
 * Created by Sanjay on 14/11/2017.
 */

public class TermsAndConditionsContract {

    public interface ITermsAndConditionsView extends IBaseView {

        void naviagteToTermsAndConditonsWebView(TermsAndConditionsModel.TermsAndConditionsRespones termsAndConditionsRespones);
    }

    public interface ITermsAndConditionsPresenter extends IBasePresenter<ITermsAndConditionsView, ITermsAndConditionsInteractor> {
        void termsAndCondition(String type, String idOrSlug);
    }

    public interface ITermsAndConditionsInteractor extends IBaseInteractor {
        Observable<TermsAndConditionsModel.TermsAndConditionsRespones> termsAndConditions(String type, String idOrSlug);
    }
}
