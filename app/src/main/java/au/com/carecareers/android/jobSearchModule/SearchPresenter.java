package au.com.carecareers.android.jobSearchModule;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.jobSearchModule.model.LocationModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nikesh on 11/29/2017.
 */

public class SearchPresenter extends BasePresenter<SearchContract.ISearchView, SearchContract.ISearchInteractor> implements SearchContract.ISearchPresenter {
    @Inject
    public SearchPresenter(SearchContract.ISearchInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    @Override
    public void loadLocations() {
        getCompositeDisposable().add(getInteractor().loadLocations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getLocationObserver()));
    }

    @Override
    public void searchJobs(String keywords, int locationId) {
        getView().showProgressDialog(R.string.msg_loading);
        getCompositeDisposable().add(getInteractor().searchJobs(keywords, locationId).subscribeOn(Schedulers.io()
        ).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getSearchObserver()));
    }

    private DisposableObserver<LocationModel.LocationResponse> getSearchObserver() {
        return new DisposableObserver<LocationModel.LocationResponse>() {
            @Override
            public void onNext(LocationModel.LocationResponse locationResponse) {
                getView().hideProgressDialog();
                getView().navigateToJobAds(locationResponse);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public DisposableObserver<LocationModel.LocationResponse> getLocationObserver() {
        return new DisposableObserver<LocationModel.LocationResponse>() {
            @Override
            public void onNext(LocationModel.LocationResponse locationResponse) {
                getView().sendCountryData(locationResponse);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
