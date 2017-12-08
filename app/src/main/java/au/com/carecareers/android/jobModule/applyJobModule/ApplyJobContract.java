package au.com.carecareers.android.jobModule.applyJobModule;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;

/**
 * Created by Nikesh on 12/1/2017.
 */

public class ApplyJobContract {
    public interface IApplyJobView extends IBaseView {
    }

    public interface IApplyJobPresenter extends IBasePresenter<ApplyJobContract.IApplyJobView, ApplyJobContract.IApplyJobInteractor> {
    }

    public interface IApplyJobInteractor extends IBaseInteractor {

    }
}
