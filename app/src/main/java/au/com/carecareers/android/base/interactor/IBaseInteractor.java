package au.com.carecareers.android.base.interactor;

import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public interface IBaseInteractor {

    ApiService getApiService();

    SharedPreferenceManager getPreferenceManager();

}
