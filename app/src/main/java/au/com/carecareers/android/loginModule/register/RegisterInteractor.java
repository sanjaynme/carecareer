package au.com.carecareers.android.loginModule.register;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.loginModule.register.model.RegisterContract;
import au.com.carecareers.android.loginModule.register.model.TaxonomyModel;
import io.reactivex.Observable;

/**
 * Created by Dell on 11/16/2017.
 */

public class RegisterInteractor extends BaseInteractor implements RegisterContract.IRegisterInteractor {
    @Inject
    public RegisterInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public Observable<TaxonomyModel.TaxonomyResponse> getStates(TaxonomyModel request) {
        return getApiService().getStates(getPreferenceManager().getStringValues(AppContract.Preferences.AUTHORIZATION_KEY));
    }
}
