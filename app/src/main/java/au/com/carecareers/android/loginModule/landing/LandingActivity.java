package au.com.carecareers.android.loginModule.landing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;

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
}
