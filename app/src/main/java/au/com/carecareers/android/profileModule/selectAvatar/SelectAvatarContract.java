package au.com.carecareers.android.profileModule.selectAvatar;


import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.profileModule.selectAvatar.model.AvatarResponse;
import io.reactivex.Observable;

/**
 * Created by Nischal Manandhar on 23/11/2017.
 */

public class SelectAvatarContract {
    public interface ISelectAvatarView extends IBaseView {
        void showProgressBar();

        void hideProgressBar();

        void setErrorMessage(int message);

        void setupAvatarList(AvatarResponse avatarResponse);

        void addMoreAvatars(AvatarResponse avatarResponse);

    }

    public interface ISelectAvatarPresenter extends IBasePresenter<ISelectAvatarView, ISelectAvatarInteractor> {
        void loadAvatarList(int page,boolean isLoadMore);
    }

    public interface ISelectAvatarInteractor extends IBaseInteractor {
        Observable<AvatarResponse> getAvatarList(int page);
    }

}
