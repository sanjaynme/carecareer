package au.com.carecareers.android.loginModule.termsAndCondition;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.contracts.UrlContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.loginModule.termsAndCondition.model.TermsAndConditionsModel;
import io.reactivex.Observable;

/**
 * Created by Sanjay  on 14/11/2017.
 */

public class TermsAndConditionInteractor extends BaseInteractor implements TermsAndConditionsContract.ITermsAndConditionsInteractor {
    private static final String TAG = "TermsAndConditionActivityInteractor";

    @Inject
    TermsAndConditionInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public Observable<TermsAndConditionsModel.TermsAndConditionsRespones> termsAndConditions(String type, String idOrSlug) {
//        return getApiService().getTermsAndConditions(getPreferenceManager().getStringValues(AppContract.Preferences.AUTHORIZATION_KEY, UrlContract.Values.AUTHORIZATION_VALUE), type, idOrSlug);
        return getApiService().getTermsAndConditions(getPreferenceManager().getStringValues(AppContract.Preferences.AUTHORIZATION_KEY, UrlContract.Values.AUTHORIZATION_VALUE));
    }
}
