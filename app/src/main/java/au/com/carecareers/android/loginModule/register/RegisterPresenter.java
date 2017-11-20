package au.com.carecareers.android.loginModule.register;

import android.util.Log;
import android.util.Patterns;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.loginModule.register.model.RegisterContract;
import au.com.carecareers.android.loginModule.register.model.RegisterModel;
import au.com.carecareers.android.loginModule.register.model.TaxonomyModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
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

    public void getStates() {
        getView().showProgressDialog(R.string.msg_loading);
        getCompositeDisposable().add(getInteractor().getStates()
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
                        if (isViewAttached()) {
                            getView().hideProgressDialog();
                            getView().showAlertDialog(R.string.err_);
                        }
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
        getView().showProgressDialog(R.string.msg_loading);
        getCompositeDisposable().add(getInteractor().register(registerRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()));
    }

    @Override
    public boolean validateSpinner(int selectedItem) {
        if (selectedItem == 0) {
            getView().showAlertDialog(R.string.err_state_name);
            return false;
        }
        return true;
    }

    private DisposableObserver<RegisterModel.RegisterResponse> getObserver() {
        return new DisposableObserver<RegisterModel.RegisterResponse>() {
            @Override
            public void onNext(RegisterModel.RegisterResponse registerResponse) {
                Log.d(TAG, "onNext: ");
                getView().hideProgressDialog();
                getView().navigateToLoginActivity(registerResponse);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
                getView().hideProgressDialog();
                getView().showAlertDialog(R.string.err_);
            }

            @Override
            public void onComplete() {
                getView().hideProgressDialog();
            }
        };
    }

}
