package au.com.carecareers.android.loginModule.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.register.injection.RegisterModule;
import au.com.carecareers.android.loginModule.register.model.RegisterContract;
import au.com.carecareers.android.loginModule.register.model.TaxonomyModel;
import au.com.carecareers.android.utilities.AppLog;
import butterknife.BindView;

/**
 * Created by Nikesh on 11/15/2017.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.IRegisterView {
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @Inject
    RegisterPresenter presenter;
    private String stateNames,stateId;

    public static void start(Context context) {
        Intent signUpIntent = new Intent();
        signUpIntent.setClass(context, RegisterActivity.class);
        context.startActivity(signUpIntent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onAttach(this);
        presenter.getStates(new TaxonomyModel());
        tvTitle.setText(getResources().getText(R.string.tv_register));
    }

    @Override
    public void navigateToMainActivity() {

    }

    @Override
    public void showStates(TaxonomyModel.TaxonomyResponse taxonomyResponse) {
        for (int i = 0; i < taxonomyResponse.getEmbedded().getTaxonomies().size(); i++) {
            stateNames = taxonomyResponse.getEmbedded().getTaxonomies().get(i).getName();
            stateId = taxonomyResponse.getEmbedded().getTaxonomies().get(i).getId().toString();
            AppLog.d("states:" + stateNames);
            AppLog.d("stateIds:" + stateId);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideRegisterSubComponent(new RegisterModule()).inject(this);
    }
}
