package au.com.carecareers.android.jobSearchModule;

import android.content.Context;
import android.content.Intent;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;

/**
 * Created by Nikesh on 11/30/2017.
 */

public class JobAdsActivity extends BaseActivity implements JobAdsContract.IJobAdsView {

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, JobAdsActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void setupToolbar() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_job_ads;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {

    }
}
