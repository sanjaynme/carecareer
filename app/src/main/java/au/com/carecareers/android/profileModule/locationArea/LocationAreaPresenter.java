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
    public void loadLocationArea(int pageNumber) {
        getView().showProgressBar();
        getCompositeDisposable().add(
                getInteractor().getLocationArea(pageNumber)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<LocationAreaResponse>() {
                            @Override
                            public void onNext(LocationAreaResponse locationAreaResponse) {
                                if (isViewAttached()) {
                                    getView().hideProgressBar();
                                    getView().showRecyclerView();
                                    getView().setupRecyclerView(locationAreaResponse);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        })
        );
    }
}
