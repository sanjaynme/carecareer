package au.com.carecareers.android.profileModule.locationArea;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.locationArea.model.LocationAreaResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nischal Manandhar on 28/11/2017.
 */
@ActivityScope
public class LocationAreaPresenter extends BasePresenter<LocationAreaContract.ILocationAreaView, LocationAreaContract.ILocationAreaInteractor> implements LocationAreaContract.ILocationAreaPresenter {
    @Inject
    public LocationAreaPresenter(LocationAreaContract.ILocationAreaInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    @Override
    public void loadLocationArea(int pageNumber, boolean isLoadMore) {
        if (isLoadMore) {
            getView().showFooterProgress();
        } else {
            getView().showProgressBar();
        }
        getCompositeDisposable().add(
                getInteractor().getLocationArea(pageNumber)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<LocationAreaResponse>() {
                            @Override
                            public void onNext(LocationAreaResponse locationAreaResponse) {
                                if (isViewAttached()) {
                                    getView().showRecyclerView();
                                    if (isLoadMore) {
                                        getView().hideFooterProgress();
                                        if (locationAreaResponse.getEmbedded() != null) {
                                            getView().addMoreItems(locationAreaResponse);
                                        }
                                    } else {
                                        getView().hideProgressBar();
                                        getView().setList(locationAreaResponse);
                                    }
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (isLoadMore) {
                                    getView().hideFooterProgress();
                                } else {
                                    getView().hideProgressBar();
                                }
                            }

                            @Override
                            public void onComplete() {
                                if (isLoadMore) {
                                    getView().hideFooterProgress();
                                } else {
                                    getView().hideProgressBar();
                                }
                            }
                        })
        );
    }

    @Override
    public void loadLocationWithoutPagination() {
        getView().showProgressBar();
        getCompositeDisposable().add(
                getInteractor().getLocationAreaWithoutPagination()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<LocationAreaResponse>() {
                            @Override
                            public void onNext(LocationAreaResponse locationAreaResponse) {
                                if (isViewAttached()) {
                                    getView().showRecyclerView();
                                    getView().hideProgressBar();
                                    getView().setList(locationAreaResponse);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (isViewAttached()) {
                                    getView().hideProgressBar();
                                }
                            }

                            @Override
                            public void onComplete() {
                                if (isViewAttached()) {
                                    getView().hideProgressBar();
                                }
                            }
                        })
        );
    }
}
