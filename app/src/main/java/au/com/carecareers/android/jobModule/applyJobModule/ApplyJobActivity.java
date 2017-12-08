package au.com.carecareers.android.jobModule.applyJobModule;

import android.content.Context;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;

/**
 * Created by Nikesh on 12/8/2017.
 */

public class ApplyJobActivity extends BaseActivity implements ApplyJobContract.IApplyJobView {
    public static void start(Context context, Integer id) {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_applied_jobs;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {

    }
}
