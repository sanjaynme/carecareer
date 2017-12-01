package au.com.carecareers.android.jobDetailsModule;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;

/**
 * Created by Nikesh on 12/1/2017.
 */

public class JobDetailsContract {
    public interface IJobDetailsView extends IBaseView {
    }

    public interface IJobDetailsPresenter extends IBasePresenter<JobDetailsContract.IJobDetailsView, JobDetailsContract.IJobDetailsInteractor> {

    }

    public interface IJobDetailsInteractor extends IBaseInteractor {
    }
}
