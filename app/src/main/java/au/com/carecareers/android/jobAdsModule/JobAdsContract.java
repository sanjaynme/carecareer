package au.com.carecareers.android.jobAdsModule;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;

/**
 * Created by Nikesh on 11/30/2017.
 */

public class JobAdsContract {
    public interface IJobAdsView extends IBaseView {

    }

    public interface IJobAdsPresenter extends IBasePresenter<JobAdsContract.IJobAdsView, JobAdsContract.IJobAdsInteractor> {

    }

    public interface IJobAdsInteractor extends IBaseInteractor {
    }
}
