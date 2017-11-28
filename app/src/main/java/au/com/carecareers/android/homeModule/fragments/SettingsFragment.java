package au.com.carecareers.android.homeModule.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseFragment;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.homeModule.SettingContract;
import au.com.carecareers.android.homeModule.SettingPresenter;
import au.com.carecareers.android.homeModule.injection.SettingsModule;
import au.com.carecareers.android.homeModule.model.TokenRefreshModel;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.changePassword.ChangePasswordActivity;
import au.com.carecareers.android.loginModule.landing.LandingActivity;
import au.com.carecareers.android.loginModule.termsAndCondition.TermsAndConditionActivity;
import au.com.carecareers.android.utilities.AppLog;
import butterknife.OnClick;

/**
 * Created by Sanjay on 11/24/2017.
 */

public class SettingsFragment extends BaseFragment implements SettingContract.ISettingView {
    @Inject
    SettingPresenter presenter;

    @Override
    public int getLayout() {
        return R.layout.fragment_settings;
    }

    @Override
    public void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideSettingsSubComponent(new SettingsModule()).inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onAttach(this);
    }


    @OnClick(R.id.tv_change_password)
    void onChangePasswordClicked() {
        ChangePasswordActivity.start(getActivity());
        transitionActivityOpen();
    }

    @OnClick(R.id.tv_logout)
    void onLogOut() {
        showWarningDialog(getResources().getString(R.string.msg_logout));
    }


    @OnClick(R.id.tv_terms_and_conditions)
    void onTermsAndConditionsClicked() {
        TermsAndConditionActivity.start(getActivity(), AppContract.Page.TERMS_AND_CONDITIONS);
        transitionActivityOpen();
    }

    @OnClick(R.id.tv_privacy_policy)
    void onPrivacyPolicyClicked() {
        TermsAndConditionActivity.start(getActivity(), AppContract.Page.PRIVACY_POLICY);
        transitionActivityOpen();
    }

    @OnClick(R.id.tv_share_app)
    void onShareAppClicked() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://www.carecareers.com.au"); // Simple text and URL to share
        sendIntent.setType("text/plain");
        this.startActivity(sendIntent);
    }

    @Override
    public void setupToolbar() {
    }

    @Override
    public void navigateToLandingActivity() {
        getActivity().finish();
        LandingActivity.start(getActivity());
        transitionActivityOpen();
    }

    @Override
    public void navigateToSettingActivity(TokenRefreshModel.TokenRefreshResponse tokenRefreshResponse) {
        AppLog.d("token reponse accesstoken:" + tokenRefreshResponse.getAccessToken());
        AppLog.d("token reponse expires in:" + tokenRefreshResponse.getExpiresIn());
        AppLog.d("token reponse refresh token:" + tokenRefreshResponse.getRefreshToken());
        AppLog.d("token reponse token type:" + tokenRefreshResponse.getTokenType());
        AppLog.d("token reponse scope:" + tokenRefreshResponse.getScope());
    }

    private void showWarningDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onLogout();
//                presenter.refreshToken();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                hideProgressDialog();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
