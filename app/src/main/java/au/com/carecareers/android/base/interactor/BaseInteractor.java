package au.com.carecareers.android.base.interactor;

import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public abstract class BaseInteractor implements IBaseInteractor {
    private ApiService mApiService;
    private SharedPreferenceManager mSharedPreferenceManager;

    public BaseInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        this.mApiService = apiService;
        this.mSharedPreferenceManager = sharedPreferenceManager;
    }

    public ApiService getApiService() {
        return mApiService;
    }

    public SharedPreferenceManager getPreferenceManager() {
        return mSharedPreferenceManager;
    }
}
