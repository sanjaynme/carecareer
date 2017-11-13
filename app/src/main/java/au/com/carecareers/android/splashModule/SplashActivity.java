package au.com.carecareers.android.splashModule;

import android.os.Bundle;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void injectComponent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
