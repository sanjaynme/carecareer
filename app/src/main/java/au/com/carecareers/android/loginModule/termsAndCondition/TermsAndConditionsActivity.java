package au.com.carecareers.android.loginModule.termsAndCondition;

import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;

/**
 * Created by Dell on 11/22/2017.
 */

public class TermsAndConditionsActivity extends BaseActivity implements  TermsAndConditionsContract.ITermsAndConditionsView {
    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {

    }
}
