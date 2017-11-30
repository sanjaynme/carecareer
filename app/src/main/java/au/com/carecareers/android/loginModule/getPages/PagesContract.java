package au.com.carecareers.android.loginModule.getPages;


import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.loginModule.getPages.model.PagesModel;
import io.reactivex.Observable;

/**
 * Created by Sanjay on 14/11/2017.
 */

public class PagesContract {

    public interface ITermsAndConditionsView extends IBaseView {

        void naviagteToTermsAndConditonsWebView(PagesModel.PagesRespones pagesRespones);
    }

    public interface IPagesPresenter extends IBasePresenter<ITermsAndConditionsView, IPagesInteractor> {
        void getPages(String type, String idOrSlug);
    }

    public interface IPagesInteractor extends IBaseInteractor {
        Observable<PagesModel.PagesRespones> getPages(String type, String idOrSlug);
    }
}
