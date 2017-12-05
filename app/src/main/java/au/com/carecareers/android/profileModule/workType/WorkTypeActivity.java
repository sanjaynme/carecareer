package au.com.carecareers.android.profileModule.workType;

import android.os.Bundle;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.utilities.ViewUtils;

public class WorkTypeActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_work_type;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setupUI(findViewById(R.id.activity_work_type), this);
    }
}
