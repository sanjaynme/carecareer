package au.com.carecareers.android.profileModule.profileSetup;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.selectAvatar.model.AvatarResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nischal Manandhar on 22/11/2017.
 */
@ActivityScope
public class ProfileSetupPresenter extends BasePresenter<ProfileSetupContract.IProfileSetupView, ProfileSetupContract.IProfileSetupInteractor> implements ProfileSetupContract.IProfileSetupPresenter {
    @Inject
    public ProfileSetupPresenter(ProfileSetupContract.IProfileSetupInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    @Override
    public void uploadImage(String imagePath) {
        getView().showProfileProgress();
        getCompositeDisposable().add(
                getInteractor().uploadImage(imagePath)
                        .flatMap(fileUploadResponse -> getInteractor().setAvatar(fileUploadResponse.file))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<AvatarResponse.Avatar>() {
                            @Override
                            public void onNext(AvatarResponse.Avatar avatar) {
                                if (isViewAttached()) {
                                    getView().hideProfileProgress();
                                    getInteractor().saveAvatarUrl(avatar.getLinks().getAvatarUrl().getHref());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (isViewAttached()) {
                                    getView().hideProfileProgress();
                                }
                            }

                            @Override
                            public void onComplete() {
                                if (isViewAttached()) {
                                    getView().hideProfileProgress();
                                }
                            }
                        })
        );
    }

    @Override
    public void setAvatar(AvatarResponse.Avatar avatar) {
        getView().showProfileProgress();
        getCompositeDisposable().add(
                getInteractor().setAvatar(avatar)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<AvatarResponse.Avatar>() {
                            @Override
                            public void onNext(AvatarResponse.Avatar avatar) {
                                if (isViewAttached()) {
                                    getView().hideProfileProgress();
                                    getInteractor().saveAvatarUrl(avatar.getLinks().getAvatarUrl().getHref());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (isViewAttached()) {
                                    getView().hideProfileProgress();
                                }
                            }

                            @Override
                            public void onComplete() {
                                if (isViewAttached()) {
                                    getView().hideProfileProgress();
                                }
                            }
                        })
        );
    }
}
