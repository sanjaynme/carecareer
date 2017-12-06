package au.com.carecareers.android.profileModule.selectAvatar;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.profileModule.selectAvatar.model.AvatarResponse;
import io.reactivex.Observable;

/**
 * Created by Nischal Manandhar on 23/11/2017.
 */
public class SelectAvatarInteractor extends BaseInteractor implements SelectAvatarContract.ISelectAvatarInteractor {
    @Inject
    public SelectAvatarInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public Observable<AvatarResponse> getAvatarList(int page) {
        return getApiService().getAvatars(getPreferenceManager().getStringValues(AppContract.Preferences.ACCESS_TOKEN), page);
    }
}
