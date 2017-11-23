package au.com.carecareers.android.profileModule.selectAvatar;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;

/**
 * Created by Nischal Manandhar on 23/11/2017.
 */

public class SelectAvatarContract {
    public interface ISelectAvatarView extends IBaseView {

    }

    public interface ISelectAvatarPresenter extends IBasePresenter<ISelectAvatarView, ISelectAvatarInteractor> {

    }

    public interface ISelectAvatarInteractor extends IBaseInteractor {

    }

}
