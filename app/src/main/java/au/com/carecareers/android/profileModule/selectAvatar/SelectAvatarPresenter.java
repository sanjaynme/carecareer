package au.com.carecareers.android.profileModule.selectAvatar;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nischal Manandhar on 23/11/2017.
 */
public class SelectAvatarPresenter extends BasePresenter<SelectAvatarContract.ISelectAvatarView, SelectAvatarContract.ISelectAvatarInteractor>
        implements SelectAvatarContract.ISelectAvatarPresenter {
    @Inject
    public SelectAvatarPresenter(SelectAvatarContract.ISelectAvatarInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }
}
