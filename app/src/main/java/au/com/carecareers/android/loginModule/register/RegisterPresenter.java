package au.com.carecareers.android.loginModule.register;

import javax.inject.Inject;

import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.loginModule.register.model.RegisterContract;
import au.com.carecareers.android.loginModule.register.model.TaxonomyModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nikesh on 11/15/2017.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.IRegisterView, RegisterContract.IRegisterInteractor>
        implements RegisterContract.IRegisterPresenter {
    @Inject
    public RegisterPresenter(RegisterContract.IRegisterInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    public void getStates(TaxonomyModel taxonomyModel) {
        getCompositeDisposable().add(getInteractor().getStates(taxonomyModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TaxonomyModel.TaxonomyResponse>() {
                    @Override
                    public void accept(TaxonomyModel.TaxonomyResponse taxonomyResponse) throws Exception {
                        if (isViewAttached()) {
                            getView().showStates(taxonomyResponse);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }
}
