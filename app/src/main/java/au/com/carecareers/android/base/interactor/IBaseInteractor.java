package au.com.carecareers.android.base.interactor;

import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.splashModule.model.AuthenticationModel;
import io.reactivex.Observable;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public interface IBaseInteractor {

    ApiService getApiService();

    SharedPreferenceManager getPreferenceManager();

    Observable<AuthenticationModel.AuthenticationResponse> getAuthenticationToken();

    void saveAuthenticationToken(AuthenticationModel.AuthenticationResponse authenticationResponse);

}
