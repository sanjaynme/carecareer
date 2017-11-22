package au.com.carecareers.android.loginModule.termsAndCondition;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.termsAndCondition.injection.TermsAndConditionsModule;
import butterknife.BindView;

/**
 * Created by Sanjay on 11/22/2017.
 */


public class TermsAndConditionActivity extends BaseActivity implements
        TermsAndConditionsContract.ITermsAndConditionsView {


    @BindView(R.id.terms_and_conditions_toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @Inject
    TermsAndConditionPresenter presenter;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TermsAndConditionActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_terms_and_conditions;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideTermsAndConditionsSubComponent(new TermsAndConditionsModule()).inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onAttach(this);
        setupToolBar();
        String type = "page";
        String idOrSlug = "privacy-policy";
        presenter.termsAndCondition(type, idOrSlug);
    }

    private void setupToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvTitle.setText(getResources().getText(R.string.tv_terms_and_conditions));
    }
}
