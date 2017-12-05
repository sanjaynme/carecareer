package au.com.carecareers.android.profileModule.professionRole;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.professionRole.model.ProfessionRoleResponse;
import io.reactivex.Observable;

/**
 * Created by Nischal Manandhar on 01/12/2017.
 */

@ActivityScope
public class ProfessionRoleInteractor extends BaseInteractor implements ProfessionRoleContract.IProfessionRoleInteractor {
    @Inject
    public ProfessionRoleInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public Observable<ProfessionRoleResponse> getProfessionRole() {
        return getApiService().getProfessionRole(
                getPreferenceManager().getStringValues(AppContract.Preferences.ACCESS_TOKEN),
                1000
        );
    }
}
