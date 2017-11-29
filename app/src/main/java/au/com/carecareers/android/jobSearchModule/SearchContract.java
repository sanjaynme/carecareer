package au.com.carecareers.android.jobSearchModule;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;

/**
 * Created by Sanjay on 11/29/2017.
 */

public class SearchContract {

    public interface ISearchView extends IBaseView {

    }

    public interface ISearchPresenter extends IBasePresenter<ISearchView, ISearchInteractor> {

    }

    public interface ISearchInteractor extends IBaseInteractor {

    }
}
