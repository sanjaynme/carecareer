package au.com.carecareers.android.profileModule.workType;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;

/**
 * Created by Nischal Manandhar on 05/12/2017.
 */

public class WorkTypeContract {
    public interface IView extends IBaseView {

    }

    public interface IPresenter extends IBasePresenter<IView, IInteractor> {

    }

    public interface IInteractor extends IBaseInteractor {

    }
}
