package au.com.carecareers.android.profileModule.profileSetup;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.injection.scope.ActivityScope;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nischal Manandhar on 22/11/2017.
 */
@ActivityScope
public class ProfileSetupPresenter extends BasePresenter<ProfileSetupContract.IProfileSetupView, ProfileSetupContract.IProfileSetupInteractor> implements ProfileSetupContract.IProfileSetupPresenter {
    @Inject
    public ProfileSetupPresenter(ProfileSetupContract.IProfileSetupInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

}
