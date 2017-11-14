package au.com.carecareers.android.loginModule.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.login.injection.LoginModule;
import au.com.carecareers.android.loginModule.login.model.LoginRequest;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.ILoginView {
    @Inject
    LoginPresenter loginPresenter;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideLoginSubComponent(new LoginModule()).inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter.onAttach(this);
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDetach();
        super.onDestroy();
    }

    @OnClick(R.id.btn_login)
    public void loginClicked() {
        loginPresenter.loginClicked(new LoginRequest());
    }

    @Override
    public void navigateToMainActivity() {

    }
}
