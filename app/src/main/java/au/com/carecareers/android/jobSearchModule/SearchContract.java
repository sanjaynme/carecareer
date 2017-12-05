package au.com.carecareers.android.jobSearchModule;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.jobAdsModule.model.JobAdsModel;
import au.com.carecareers.android.jobSearchModule.model.LocationModel;
import io.reactivex.Observable;

/**
 * Created by Sanjay on 11/29/2017.
 */

public class SearchContract {

    public interface ISearchView extends IBaseView {

        void sendCountryData(LocationModel.LocationResponse locationResponse);

        void navigateToJobAds(JobAdsModel.JobAdsResponse jobAdsResponse);
    }

    public interface ISearchPresenter extends IBasePresenter<ISearchView, ISearchInteractor> {

        void loadLocations();

        void searchJobs(String keywords, int locationId);

        void searchJobs(JobAdsModel.JobAdsRequest jobAdsRequestModel);
    }

    public interface ISearchInteractor extends IBaseInteractor {

        Observable<LocationModel.LocationResponse> loadLocations();

        Observable<JobAdsModel.JobAdsResponse> searchJobAds(JobAdsModel.JobAdsRequest jobAdsRequest);

    }
}
