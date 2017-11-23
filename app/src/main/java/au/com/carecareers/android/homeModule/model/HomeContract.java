package au.com.carecareers.android.homeModule.model;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;

/**
 * Created by Nikesh on 11/15/2017.
 */

public interface HomeContract {
    public interface IHomeView extends IBaseView {

    }

    public interface IHomePresenter extends IBasePresenter<HomeContract.IHomeView, HomeContract.IHomeInteractor> {

    }

    public interface IHomeInteractor extends IBaseInteractor {


    }
}
