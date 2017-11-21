package au.com.carecareers.android.loginModule.forgotPassword;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.forgotPassword.injection.ForgotPasswordModule;
import butterknife.BindView;

/**
 * Created by Dell on 11/20/2017.
 */

public class ForgotPasswordActivity extends BaseActivity implements ForgotPasswordContract.IForgotPasswordView {
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @BindView(R.id.forgot_password_toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_forgot_email)
    EditText etForgotEmail;

    @Inject
    ForgotPasswordPresenter presenter;

    @Override
    public int getLayout() {
        return R.layout.activity_forgot_password;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideForgotPasswordSubComponent(new ForgotPasswordModule()).inject(this);
    }

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ForgotPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onAttach(this);
        setupToolBar();
        String forgotEmail = etForgotEmail.getText().toString().trim();
//        presenter.forgotPassword(forgotEmail);
    }

    private void setupToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvTitle.setText(getResources().getText(R.string.tv_forgot_password));
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                transitionBackPressed();
                etForgotEmail.getText().clear();
                break;
        }
        return true;
    }
}
