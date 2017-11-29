package au.com.carecareers.android.jobSearchModule;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.jobSearchModule.model.LocationModel;
import io.reactivex.Observable;

/**
 * Created by Sanjay on 11/29/2017.
 */

public class SearchInteractor extends BaseInteractor implements SearchContract.ISearchInteractor {

    @Inject
    public SearchInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public Observable<LocationModel.LocationResponse> loadLocations() {
        int country_id =1;
        int page=1;
        return getApiService().getLocations(getPreferenceManager().getStringValues(AppContract.Preferences.AUTHORIZATION_KEY),country_id,page);
    }
}