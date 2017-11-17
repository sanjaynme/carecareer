package au.com.carecareers.android.loginModule.landing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.login.LoginActivity;
import au.com.carecareers.android.loginModule.register.RegisterActivity;
import butterknife.OnClick;

public class LandingActivity extends BaseActivity {
    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LandingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_landing;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btn_login)
    void loginClicked() {
        LoginActivity.start(LandingActivity.this);
        transitionActivityOpen();
    }

    @OnClick(R.id.btn_register)
    void createAccountClicked() {
        RegisterActivity.start(LandingActivity.this);
        transitionActivityOpen();
    }
}
