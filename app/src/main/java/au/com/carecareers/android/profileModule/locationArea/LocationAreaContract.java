package au.com.carecareers.android.profileModule.locationArea;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.profileModule.locationArea.model.LocationAreaResponse;
import io.reactivex.Observable;

/**
 * Created by Nischal Manandhar on 28/11/2017.
 */

public class LocationAreaContract {
    public interface ILocationAreaView extends IBaseView {
        void showProgressBar();

        void hideProgressBar();

        void showFooterProgress();

        void hideFooterProgress();

        void setErrorMessage(int message);

        void hideErrorMessage();

        void showRecyclerView();

        void hideRecyclerView();

        void setList(LocationAreaResponse locationAreaResponse);

        void addMoreItems(LocationAreaResponse locationAreaResponse);
    }

    public interface ILocationAreaPresenter extends IBasePresenter<ILocationAreaView, ILocationAreaInteractor> {
        void loadLocationArea(int pageNumber, boolean isLoadMore);

        void loadLocationWithoutPagination();
    }

    public interface ILocationAreaInteractor extends IBaseInteractor {
        Observable<LocationAreaResponse> getLocationArea(int pageNumber);

        Observable<LocationAreaResponse> getLocationAreaWithoutPagination();
    }
}
