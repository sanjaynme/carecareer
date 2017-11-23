package au.com.carecareers.android.loginModule.changePassword;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.changePassword.injection.ChangePasswordModule;
import butterknife.BindView;

/**
 * Created by Dell on 11/20/2017.
 */

public class ChangePasswordActivity extends BaseActivity implements ChangePasswordContract.IChangePasswordView {
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @BindView(R.id.change_password_toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_current_password)
    EditText etChangePassword;

    @Inject
    ChangePasswordPresenter presenter;

    @Override
    public int getLayout() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideChangePasswordSubComponent(new ChangePasswordModule()).inject(this);
    }

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ChangePasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onAttach(this);
//        presenter.forgotPassword(forgotEmail);
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvTitle.setText(getResources().getText(R.string.tv_forgot_password));
    }
}
