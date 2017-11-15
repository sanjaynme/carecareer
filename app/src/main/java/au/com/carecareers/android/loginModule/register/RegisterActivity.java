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
import au.com.carecareers.android.loginModule.register.model.RegisterContract;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Nikesh on 11/15/2017.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.IRegisterView {
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @Inject
    RegisterPresenter signUpPresenter;

    public static void start(Context context) {
        Intent signUpIntent = new Intent();
        signUpIntent.setClass(context, RegisterActivity.class);
        context.startActivity(signUpIntent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvTitle.setText("Register");
    }

    @Override
    public void navigateToMainActivity() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_signup;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {

    }

    @OnClick(R.id.iv_back_arrow)
    public void onBackArrowClicked() {
        onBackPressed();
//        edtEmail.getText().clear();
//        edtPassword.getText().clear();
    }
}
