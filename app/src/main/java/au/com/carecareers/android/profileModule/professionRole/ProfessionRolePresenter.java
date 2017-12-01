package au.com.carecareers.android.profileModule.professionRole;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.locationArea.model.LocationAreaResponse;
import au.com.carecareers.android.profileModule.professionRole.model.ProfessionRoleResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nischal Manandhar on 01/12/2017.
 */
@ActivityScope
public class ProfessionRolePresenter extends BasePresenter<ProfessionRoleContract.IProfessionRoleView, ProfessionRoleContract.IProfessionRoleInteractor> implements ProfessionRoleContract.IProfessionRolePresenter {
    @Inject
    public ProfessionRolePresenter(ProfessionRoleContract.IProfessionRoleInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    @Override
    public void loadProfessionsRoles() {
        getView().showProgressBar();
        getCompositeDisposable().add(
                getInteractor().getProfessionRole()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<ProfessionRoleResponse>() {
                            @Override
                            public void onNext(ProfessionRoleResponse professionRoleResponse) {
                                if (isViewAttached()) {
                                    getView().showRecyclerView();
                                    getView().hideProgressBar();
                                    getView().setList(professionRoleResponse);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (isViewAttached()) {
                                    getView().hideProgressBar();
                                }
                            }

                            @Override
                            public void onComplete() {
                                if (isViewAttached()) {
                                    getView().hideProgressBar();
                                }
                            }
                        })
        );
    }
}
