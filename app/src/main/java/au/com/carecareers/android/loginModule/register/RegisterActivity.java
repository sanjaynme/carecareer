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
        tvTitle.setText(getResources().getText(R.string.tv_register));
    }

    @Override
    public void navigateToMainActivity() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {

    }

    @Override
    public void setupToolbar() {

    }
}
