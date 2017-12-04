package au.com.carecareers.android.profileModule.profileSetup;

import java.io.File;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.contracts.UrlContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.selectAvatar.model.AvatarRequest;
import au.com.carecareers.android.profileModule.selectAvatar.model.AvatarResponse;
import au.com.carecareers.android.profileModule.selectAvatar.model.FileUploadResponse;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Nischal Manandhar on 22/11/2017.
 */
@ActivityScope
public class ProfileSetupInteractor extends BaseInteractor implements ProfileSetupContract.IProfileSetupInteractor {
    @Inject
    public ProfileSetupInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public Observable<FileUploadResponse> uploadImage(String imagePath) {
        File file = new File(imagePath);
        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file tvName
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        return getApiService().uploadImageFile(
                getPreferenceManager().getStringValues(AppContract.Preferences.ACCESS_TOKEN),
                body
        );
    }

    @Override
    public Observable<AvatarResponse.Avatar> setAvatar(String filePath) {
        AvatarRequest avatarRequest = new AvatarRequest();
        avatarRequest.avatar = "file";
        avatarRequest.file = filePath;
        return getApiService().setAvatar(
                getPreferenceManager().getStringValues(AppContract.Preferences.ACCESS_TOKEN),
                UrlContract.Values.CANDIDATE_ID,
                avatarRequest
        );
    }

    @Override
    public Observable<AvatarResponse.Avatar> setAvatar(AvatarResponse.Avatar avatar) {
        AvatarRequest avatarRequest = new AvatarRequest();
        avatarRequest.avatar = String.valueOf(avatar.getId());
        return getApiService().setAvatar(
                getPreferenceManager().getStringValues(AppContract.Preferences.ACCESS_TOKEN),
                UrlContract.Values.CANDIDATE_ID,
                avatarRequest
        );
    }

    @Override
    public void saveAvatarUrl(String url) {
        getPreferenceManager().setKeyValues(AppContract.Preferences.AVATAR_URL, url);
    }
}
