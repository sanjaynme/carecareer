package au.com.carecareers.android.loginModule.register;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.contracts.UrlContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.loginModule.register.model.RegisterContract;
import au.com.carecareers.android.loginModule.register.model.RegisterModel;
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
    public Observable<TaxonomyModel.TaxonomyResponse> getStates() {
        return getApiService().getStates(getPreferenceManager().getStringValues(AppContract.Preferences.AUTHORIZATION_KEY, UrlContract.Values.AUTHORIZATION_VALUE));
    }

    @Override
    public Observable<RegisterModel.RegisterResponse> register(RegisterModel.RegisterRequest request) {
        return getApiService().register(getPreferenceManager().getStringValues(AppContract.Preferences.AUTHORIZATION_KEY, UrlContract.Values.AUTHORIZATION_VALUE), request);
    }

    @Override
    public void saveRegisterResponse(RegisterModel.RegisterResponse registerResponse) {

    }
}
