package au.com.carecareers.android.loginModule.changePassword;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.changePassword.injection.ChangePasswordModule;
import au.com.carecareers.android.utilities.ViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Dell on 11/20/2017.
 */

public class ChangePasswordActivity extends BaseActivity implements ChangePasswordContract.IChangePasswordView {
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @BindView(R.id.btn_show_hide_old_password)
    ImageButton btnShowHideOldPassword;

    @BindView(R.id.btn_show_hide_new_password)
    ImageButton btnShowHideNewPassword;

    @BindView(R.id.change_password_toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_old_password)
    EditText etOldPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;

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
        ViewUtils.setupUI(findViewById(R.id.activity_change_password), this);
        presenter.onAttach(this);
        btnShowHideOldPassword.setImageResource(R.drawable.ic_eye);
        btnShowHideNewPassword.setImageResource(R.drawable.ic_eye);

//        presenter.forgotPassword(forgotEmail);
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvTitle.setText(getResources().getText(R.string.tv_settings_change_password));
    }

    @OnClick(R.id.submit_view_change_password)
    public void loginClicked() {
        Toast.makeText(this, "Under Development", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.btn_show_hide_old_password)
    void showHideOldPassword() {
        if (etOldPassword.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            etOldPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            btnShowHideOldPassword.setImageResource(R.drawable.ic_eye_slash);
        } else if (etOldPassword.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
            etOldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            btnShowHideOldPassword.setImageResource(R.drawable.ic_eye);
        }
    }

    @OnClick(R.id.btn_show_hide_new_password)
    void showHideNewPassword() {
        if (etNewPassword.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            etNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            btnShowHideNewPassword.setImageResource(R.drawable.ic_eye_slash);
        } else if (etNewPassword.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
            etNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            btnShowHideNewPassword.setImageResource(R.drawable.ic_eye);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                transitionBackPressed();
                break;
        }
        return true;
    }

}
