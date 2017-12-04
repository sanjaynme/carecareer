package au.com.carecareers.android.settingsModule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.customViews.EbAlertDialog;
import au.com.carecareers.android.homeModule.model.TokenRefreshModel;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.changePassword.ChangePasswordActivity;
import au.com.carecareers.android.loginModule.login.LoginActivity;
import au.com.carecareers.android.loginModule.termsAndCondition.TermsAndConditionActivity;
import au.com.carecareers.android.profileModule.uploadFile.UploadFileActivity;
import au.com.carecareers.android.settingsModule.injection.SettingsModule;
import au.com.carecareers.android.utilities.AppLog;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Sanjay on 11/24/2017.
 */

public class SettingsActivity extends BaseActivity implements SettingContract.ISettingView {
    @Inject
    SettingPresenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SettingsActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_settings;
    }

    @Override
    public void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideSettingsSubComponent(new SettingsModule()).inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onAttach(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return false;
    }

    @OnClick(R.id.tv_change_password)
    void onChangePasswordClicked() {
        ChangePasswordActivity.start(this);
        transitionActivityOpen();
    }

    @OnClick(R.id.tv_upload_file)
    void uploadFile() {
        UploadFileActivity.start(this);
        transitionActivityOpen();
    }

    @OnClick(R.id.tv_logout)
    void onLogOut() {
        showWarningDialog(getString(R.string.msg_logout));
    }

    @OnClick(R.id.tv_terms_and_conditions)
    void onTermsAndConditionsClicked() {
        TermsAndConditionActivity.start(this, AppContract.Page.TERMS_AND_CONDITIONS);
        transitionActivityOpen();
    }

    @OnClick(R.id.tv_privacy_policy)
    void onPrivacyPolicyClicked() {
        TermsAndConditionActivity.start(this, AppContract.Page.PRIVACY_POLICY);
        transitionActivityOpen();
    }

    @OnClick(R.id.tv_share_app)
    void onShareAppClicked() {
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://www.carecareers.com.au"); // Simple text and URL to share
//        sendIntent.setType("text/plain");
//        this.startActivity(sendIntent);
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolbarTitle.setText(getString(R.string.tv_home));
    }

    @Override
    public void navigateToLoginActivity() {
        finish();
        LoginActivity.start(this);
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
        EbAlertDialog.showLogOutWithCallback(this, message, new EbAlertDialog.ConfirmationDialogCallback() {
            @Override
            public void onOkClicked() {
                presenter.onLogout();
            }

            @Override
            public void onCancelClicked() {

            }
        });
    }
}
