package au.com.carecareers.android.profileModule.selectAvatar;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.profileModule.selectAvatar.model.AvatarResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nischal Manandhar on 23/11/2017.
 */
public class SelectAvatarPresenter extends BasePresenter<SelectAvatarContract.ISelectAvatarView, SelectAvatarContract.ISelectAvatarInteractor>
        implements SelectAvatarContract.ISelectAvatarPresenter {
    @Inject
    public SelectAvatarPresenter(SelectAvatarContract.ISelectAvatarInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    @Override
    public void loadAvatarList(int page, boolean isLoadMore) {
        getView().showProgressBar();
        getCompositeDisposable().add(getInteractor().getAvatarList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<AvatarResponse>() {
                    @Override
                    public void onNext(AvatarResponse avatarResponse) {
                        if (isViewAttached()) {
                            getView().hideProgressBar();
                            if(isLoadMore){
                                getView().addMoreAvatars(avatarResponse);
                            }else {
                                getView().setupAvatarList(avatarResponse);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().hideProgressBar();
                            if (e instanceof HttpException) {
                                getView().showError(((HttpException) e).response().errorBody(), AppContract.ErrorTypes.SELECT_AVATAR);
                            }
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (isViewAttached()) {
                            getView().hideProgressBar();
                        }
                    }
                }));
    }
}
