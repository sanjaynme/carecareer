package au.com.carecareers.android.loginModule.getPages;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.loginModule.getPages.model.PagesModel;
import io.reactivex.Observable;

/**
 * Created by Sanjay  on 14/11/2017.
 */

public class PagesInteractor extends BaseInteractor implements PagesContract.IPagesInteractor {
    private static final String TAG = "TermsAndConditionActivityInteractor";

    @Inject
    PagesInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public Observable<PagesModel.PagesRespones> getPages(String type, String idOrSlug) {
        return getApiService().getPages(getPreferenceManager().getStringValues(AppContract.Preferences.AUTHORIZATION_KEY), type, idOrSlug);
    }
}
