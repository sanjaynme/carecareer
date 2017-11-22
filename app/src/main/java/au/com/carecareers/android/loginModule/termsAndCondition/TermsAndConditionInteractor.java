package au.com.carecareers.android.loginModule.termsAndCondition;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;

/**
 * Created by Sanjay  on 14/11/2017.
 */

public class TermsAndConditionInteractor extends BaseInteractor implements TermsAndConditionsContract.ITermsAndConditionsContractInteractor {
    private static final String TAG = "TermsAndConditionActivityInteractor";

    @Inject
    TermsAndConditionInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }


}
