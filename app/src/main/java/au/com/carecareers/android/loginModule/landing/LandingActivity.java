package au.com.carecareers.android.loginModule.landing;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Window;
import android.view.WindowManager;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.login.LoginActivity;
import au.com.carecareers.android.loginModule.register.RegisterActivity;
import butterknife.OnClick;

public class LandingActivity extends BaseActivity {
    public static void start(Context context) {
        Intent intent = new Intent(context, LandingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_landing;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
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

    @Override
    public void setupToolbar() {

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}
