package au.com.carecareers.android.loginModule.termsAndCondition;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.presenter.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Sanjay on 14/11/2017.
 */

public class TermsAndConditionPresenter extends BasePresenter<TermsAndConditionsContract.ITermsAndConditionsView, TermsAndConditionsContract.ITermsAndConditionsInteractor>
        implements TermsAndConditionsContract.ITermsAndConditionsPresenter {

    @Inject
    public TermsAndConditionPresenter(TermsAndConditionsContract.ITermsAndConditionsInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    @Override
    public void termsAndCondition(String type, String idOrSlug) {
        getView().showProgressDialog(R.string.msg_loading);

    }

    private void getObserver() {
    }


}
