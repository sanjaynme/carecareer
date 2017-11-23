package au.com.carecareers.android.loginModule.termsAndCondition;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Sanjay on 14/11/2017.
 */

public class TermsAndConditionPresenter extends BasePresenter<TermsAndConditionsContract.ITermsAndConditionsContractView, TermsAndConditionsContract.ITermsAndConditionsContractInteractor>
        implements TermsAndConditionsContract.ITermsAndConditionsContractPresenter {

    @Inject
    public TermsAndConditionPresenter(TermsAndConditionsContract.ITermsAndConditionsContractInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }
}
