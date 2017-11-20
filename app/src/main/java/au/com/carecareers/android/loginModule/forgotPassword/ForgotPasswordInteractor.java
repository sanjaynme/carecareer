package au.com.carecareers.android.loginModule.forgotPassword;

import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;

/**
 * Created by Dell on 11/20/2017.
 */

public class ForgotPasswordInteractor implements ForgotPasswordContract.IForgotPasswordInteractor {
    @Override
    public ApiService getApiService() {
        return null;
    }

    @Override
    public SharedPreferenceManager getPreferenceManager() {
        return null;
    }
}
