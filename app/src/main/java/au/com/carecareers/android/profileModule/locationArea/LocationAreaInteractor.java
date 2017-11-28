package au.com.carecareers.android.profileModule.locationArea;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.locationArea.model.LocationAreaResponse;
import io.reactivex.Observable;

/**
 * Created by Nischal Manandhar on 28/11/2017.
 */
@ActivityScope
public class LocationAreaInteractor extends BaseInteractor implements LocationAreaContract.ILocationAreaInteractor {
    @Inject
    public LocationAreaInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public Observable<LocationAreaResponse> getLocationArea(int pageNumber) {
        return getApiService().getLocationArea(
                getPreferenceManager().getStringValues(AppContract.Preferences.ACCESS_TOKEN),
                pageNumber,
                pageNumber
        );
    }
}
