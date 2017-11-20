package au.com.carecareers.android.loginModule.forgotPassword;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Dell on 11/20/2017.
 */

public class ForgotPasswordPresenter implements ForgotPasswordContract.IForgotPasswordPresenter {
    @Override
    public void onAttach(ForgotPasswordContract.IForgotPasswordView view) {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public boolean isViewAttached() {
        return false;
    }

    @Override
    public ForgotPasswordContract.IForgotPasswordView getView() {
        return null;
    }

    @Override
    public ForgotPasswordContract.IForgotPasswordInteractor getInteractor() {
        return null;
    }

    @Override
    public CompositeDisposable getCompositeDisposable() {
        return null;
    }
}
