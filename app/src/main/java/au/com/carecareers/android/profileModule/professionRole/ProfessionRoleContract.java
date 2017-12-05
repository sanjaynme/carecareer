package au.com.carecareers.android.profileModule.professionRole;

import au.com.carecareers.android.base.interactor.IBaseInteractor;
import au.com.carecareers.android.base.presenter.IBasePresenter;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.profileModule.professionRole.model.ProfessionRoleResponse;
import io.reactivex.Observable;

/**
 * Created by Nischal Manandhar on 01/12/2017.
 */

public class ProfessionRoleContract {
    public interface IProfessionRoleView extends IBaseView {
        void showProgressBar();

        void hideProgressBar();

        void setErrorMessage(int message);

        void hideErrorMessage();

        void showRecyclerView();

        void hideRecyclerView();

        void setList(ProfessionRoleResponse professionRoleResponse);
    }

    public interface IProfessionRolePresenter extends IBasePresenter<IProfessionRoleView, IProfessionRoleInteractor> {
        void loadProfessionsRoles();
    }

    public interface IProfessionRoleInteractor extends IBaseInteractor {
        Observable<ProfessionRoleResponse> getProfessionRole();
    }
}
