package au.com.carecareers.android.profileModule.profileSetup;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.profileModule.selectAvatar.model.AvatarResponse;
import au.com.carecareers.android.profileModule.selectAvatar.model.FileUploadResponse;
import io.reactivex.Observable;

/**
 * Created by Nischal Manandhar on 17/11/2017.
 */

public class ProfileSetupContract {
    public interface IProfileSetupView extends IBaseView {
        void setProfileImage(String imagePath, boolean isAvatar);

        void showProfileProgress();

        void hideProfileProgress();

        void setPhoneNumber(String phoneNumber);

        void setPreferredLocation(String location);

        void setLocationArea(String locationArea);

        void setProfessionRole(String professionRole);

        void setWorkType(String workType);
    }

    public interface IProfileSetupPresenter extends IBasePresenter<IProfileSetupView, IProfileSetupInteractor> {
        void uploadImage(String imagePath);

        void setAvatar(AvatarResponse.Avatar avatar);
    }

    public interface IProfileSetupInteractor extends IBaseInteractor {
        Observable<FileUploadResponse> uploadImage(String imagePath);

        Observable<AvatarResponse.Avatar> setAvatar(String filePath);

        Observable<AvatarResponse.Avatar> setAvatar(AvatarResponse.Avatar avatar);

        void saveAvatarUrl(String url);
    }
}
