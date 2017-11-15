package au.com.carecareers.android.loginModule.register;

import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.loginModule.register.model.RegisterContract;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nikesh on 11/15/2017.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.IRegisterView, RegisterContract.IRegisterInteractor> {
    public RegisterPresenter(RegisterContract.IRegisterInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }
}
