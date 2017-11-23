package au.com.carecareers.android.profileModule.selectAvatar;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;

/**
 * Created by Nischal Manandhar on 23/11/2017.
 */
public class SelectAvatarInteractor extends BaseInteractor implements SelectAvatarContract.ISelectAvatarInteractor {
    @Inject
    public SelectAvatarInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }
}
