package au.com.carecareers.android.loginModule.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.forgotPassword.ForgotPasswordActivity;
import au.com.carecareers.android.loginModule.login.injection.LoginModule;
import au.com.carecareers.android.loginModule.login.model.LoginModel;
import au.com.carecareers.android.profileModule.profileSetup.ProfileSetupActivity;
import au.com.carecareers.android.utilities.ViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.ILoginView {
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @BindView(R.id.login_toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.btn_show_hide_login_password)
    ImageButton btnShowHidePassword;

    @Inject
    LoginPresenter presenter;

    LoginModel.LoginRequest loginModel;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideLoginSubComponent(new LoginModule()).inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setupUI(findViewById(R.id.activity_login), this);
        presenter.onAttach(this);
        loginModel = new LoginModel.LoginRequest();
        btnShowHidePassword.setImageResource(R.drawable.ic_eye);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @OnClick(R.id.btn_login)
    public void loginClicked() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (presenter.validateFields(username, password)) {
            presenter.loginClicked(username, password);
        }
    }

    @OnClick(R.id.tv_forgot_password)
    void forgetPasswordButton() {
        ForgotPasswordActivity.start(LoginActivity.this);
        transitionActivityOpen();
    }

    @Override
    public void navigateToHomeActivity() {
        etUsername.getText().clear();
        etPassword.getText().clear();
        finishAffinity();
        // HomeActivity.start(LoginActivity.this);
        ProfileSetupActivity.start(LoginActivity.this);
        transitionActivityOpen();
    }

    @OnClick(R.id.btn_show_hide_login_password)
    void showHidePassword() {
        if (etPassword.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            btnShowHidePassword.setImageResource(R.drawable.ic_eye_slash);
        } else if (etPassword.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            btnShowHidePassword.setImageResource(R.drawable.ic_eye);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                transitionBackPressed();
                etUsername.getText().clear();
                etPassword.getText().clear();
                break;
        }
        return true;
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvTitle.setText(getResources().getText(R.string.tv_login));
    }
}
