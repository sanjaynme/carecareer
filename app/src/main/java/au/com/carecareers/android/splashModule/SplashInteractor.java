package au.com.carecareers.android.splashModule;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.contracts.UrlContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.splashModule.model.AuthenticationModel;
import au.com.carecareers.android.utilities.AppLog;
import io.reactivex.Observable;

/**
 * Created by Sanjay on 11/16/2017.
 */

public class SplashInteractor extends BaseInteractor implements SplashContract.ISplashInteractor {

    @Inject
    SplashInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }
}
