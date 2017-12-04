package au.com.carecareers.android.profileModule.uploadFile;

import java.io.File;

import javax.inject.Inject;

import au.com.carecareers.android.base.interactor.BaseInteractor;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.contracts.UrlContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.data.rest.ApiService;
import au.com.carecareers.android.injection.scope.ActivityScope;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Nischal Manandhar on 04/12/2017.
 */
@ActivityScope
public class UploadFileInteractor extends BaseInteractor implements UploadFileContract.IUploadFileInteractor {
    @Inject
    public UploadFileInteractor(ApiService apiService, SharedPreferenceManager sharedPreferenceManager) {
        super(apiService, sharedPreferenceManager);
    }

    @Override
    public void uploadFile(String filePath) {
        File file = new File(filePath);
        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file tvName
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        getApiService().uploadFile(
                getPreferenceManager().getStringValues(AppContract.Preferences.ACCESS_TOKEN),
                UrlContract.Values.CANDIDATE_ID,
                1,
                1,
                body
        );
    }
}
