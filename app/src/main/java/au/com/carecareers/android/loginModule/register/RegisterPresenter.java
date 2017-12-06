package au.com.carecareers.android.loginModule.register;

import android.util.Log;
import android.util.Patterns;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.util.regex.Pattern;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.loginModule.register.model.RegisterContract;
import au.com.carecareers.android.loginModule.register.model.RegisterModel;
import au.com.carecareers.android.loginModule.register.model.TaxonomyModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Sanjay on 11/15/2017.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.IRegisterView, RegisterContract.IRegisterInteractor>
        implements RegisterContract.IRegisterPresenter {

    @Inject
    public RegisterPresenter(RegisterContract.IRegisterInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    public void getStates() {
        getCompositeDisposable().add(getInteractor().getStates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(taxonomyResponse -> {
                    if (isViewAttached()) {
                        getView().setUpStatesSpinner(taxonomyResponse);
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        Log.d(TAG, "onError: ");
                        ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
                        getView().showError(responseBody, AppContract.ErrorTypes.REGISTER);
                    }
                }));
    }

    public void getStatesWithAuthTokenCheck() {
        getCompositeDisposable().add(getInteractor().isPreferenceCleared()
                .flatMap(aBoolean -> {
                    if (aBoolean) {
                        return getInteractor().getAuthenticationToken()
                                .flatMap(authenticationResponse -> {
                                    getInteractor().saveAuthenticationToken(authenticationResponse);
                                    return getInteractor().getStates();
                                });
                    } else {
                        return getInteractor().getStates();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<TaxonomyModel.TaxonomyResponse>() {
                    @Override
                    public void onNext(TaxonomyModel.TaxonomyResponse taxonomyResponse) {
                        if (isViewAttached()) {
                            getView().setUpStatesSpinner(taxonomyResponse);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            Log.d(TAG, "onError: ");
                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
                            getView().showError(responseBody, AppContract.ErrorTypes.REGISTER);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public boolean validateFields(RegisterModel.RegisterRequest registerModel) {
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
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
        } else if (!UpperCasePatten.matcher(registerModel.getPassword()).find()) {
            getView().showAlertDialog(R.string.err_password_uppercase);
            return false;
        }
        return true;
    }

    @Override
    public void sendRegisterDetails(RegisterModel.RegisterRequest registerRequest) {
        getView().showProgressDialog(R.string.msg_loading);
        getCompositeDisposable().add(getInteractor().isPreferenceCleared()
                .flatMap(aBoolean -> {
                    if (aBoolean) {
                        return getInteractor().getAuthenticationToken()
                                .flatMap(authenticationResponse -> {
                                    getInteractor().saveAuthenticationToken(authenticationResponse);
                                    return getInteractor().register(registerRequest);
                                });
                    } else {
                        return getInteractor().register(registerRequest);
                    }
                })
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
                getInteractor().saveRegisterResponse(registerResponse);
                getView().hideProgressDialog();
                getView().navigateToLoginActivity(registerResponse);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.d(TAG, "onError: ");
                ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
                getView().hideProgressDialog();
                getView().showError(responseBody, AppContract.ErrorTypes.REGISTER);
            }


            @Override
            public void onComplete() {
                getView().hideProgressDialog();
            }
        };
    }
}
