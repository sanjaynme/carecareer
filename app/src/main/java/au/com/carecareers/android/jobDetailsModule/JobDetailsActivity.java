package au.com.carecareers.android.jobDetailsModule;

import android.content.Context;
import android.content.Intent;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;

/**
 * Created by Nikesh on 12/1/2017.
 */

public class JobDetailsActivity extends BaseActivity implements JobDetailsContract.IJobDetailsView {

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, JobDetailsActivity.class);
        context.startActivity(intent);
    }

    /*public static void start(Context context, int jobAdId, int position) {
        Intent intent = new Intent();
        intent.setClass(context, JobDetailsActivity.class);
        context.startActivity(intent);
    }*/

    @Override
    public void setupToolbar() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_job_details;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {

    }


}
