package au.com.carecareers.android.loginModule.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.login.injection.LoginModule;
import au.com.carecareers.android.loginModule.login.model.LoginRequest;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.ILoginView {
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    TextView etPassword;

    @BindView(R.id.btn_show_hide_password)
    ImageButton btnShowHidePassword;

    @Inject
    LoginPresenter loginPresenter;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginActivity.class);
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
        tvTitle.setText(getResources().getText(R.string.tv_login));
        loginPresenter.onAttach(this);
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDetach();
        super.onDestroy();
    }


    @OnClick(R.id.submit_view_register)
    public void loginClicked() {
        loginPresenter.loginClicked(new LoginRequest());
    }

    @Override
    public void navigateToMainActivity() {

    }

    @OnClick(R.id.btn_show_hide_password)
    void showHidePassword() {
        if (etPassword.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            btnShowHidePassword.setImageResource(R.drawable.ic_white_custom_hide);
        } else if (etPassword.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            btnShowHidePassword.setImageResource(R.drawable.ic_white_eye);
        }
    }

    @Override
    public void setupToolbar() {

    }
}
