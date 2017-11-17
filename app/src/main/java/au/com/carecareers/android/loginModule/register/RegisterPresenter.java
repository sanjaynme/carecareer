package au.com.carecareers.android.loginModule.register;

import android.util.Patterns;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.loginModule.register.model.RegisterContract;
import au.com.carecareers.android.loginModule.register.model.RegisterModel;
import au.com.carecareers.android.loginModule.register.model.TaxonomyModel;
import au.com.carecareers.android.utilities.AppLog;
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
//        getView().showProgressDialog("Loading...");
        getCompositeDisposable().add(getInteractor().getStates(taxonomyModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TaxonomyModel.TaxonomyResponse>() {
                    @Override
                    public void accept(TaxonomyModel.TaxonomyResponse taxonomyResponse) throws Exception {
                        if (isViewAttached()) {
                            getView().hideProgressDialog();
                            getView().setUpStatesSpinner(taxonomyResponse);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }

    @Override
    public boolean validateFields(RegisterModel.RegisterRequest registerModel) {
        if (registerModel.getFirstName().isEmpty()) {
            getView().showAlertDialog(R.string.err_firstname);
            return false;
        } else if (registerModel.getLastName().isEmpty()) {
            getView().showAlertDialog(R.string.err_lastname);
            return false;
        } else if (registerModel.getEmail().isEmpty()) {
            getView().showAlertDialog(R.string.err_email);
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(registerModel.getEmail()).matches()) {
            getView().showAlertDialog(R.string.err_email_valid);
            return false;
        } else if (registerModel.getPassword().length() == 0) {
            getView().showAlertDialog(R.string.err_password);
            return false;
        } else if (registerModel.getPassword().length() < 8) {
            getView().showAlertDialog(R.string.err_password_length);
            return false;
        }
        return true;
    }

    @Override
    public void sendRegisterDetails(RegisterModel.RegisterRequest registerRequest) {
//        getView().showProgressDialog(Integer.parseInt("Signing up..."));
        getCompositeDisposable().add(getInteractor().register(registerRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterModel.RegisterResponse>() {
                    @Override
                    public void accept(RegisterModel.RegisterResponse registerResponse) throws Exception {
                        if (isViewAttached()) {
                            getView().hideProgressDialog();
                            getView().navigateToLoginActivity(registerResponse);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        AppLog.d(throwable.getMessage());
                    }
                }));
    }

}
