package au.com.carecareers.android.loginModule.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.login.LoginActivity;
import au.com.carecareers.android.loginModule.register.adapter.SpinnerAdapter;
import au.com.carecareers.android.loginModule.register.injection.RegisterModule;
import au.com.carecareers.android.loginModule.register.model.RegisterContract;
import au.com.carecareers.android.loginModule.register.model.RegisterModel;
import au.com.carecareers.android.loginModule.register.model.TaxonomyModel;
import au.com.carecareers.android.utilities.AppLog;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Nikesh on 11/15/2017.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.IRegisterView {
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @BindView(R.id.et_email)
    EditText etEmail;

    @BindView(R.id.et_firstname)
    EditText etFirstName;

    @BindView(R.id.et_lastname)
    EditText etLastName;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.spinner_state)
    Spinner spinnerState;

    @Inject
    RegisterPresenter presenter;

    @BindView(R.id.btn_show_hide_register_password)
    ImageButton btnShowHidePassword;

    ArrayList<String> statesList = new ArrayList<>();
    RegisterModel.RegisterRequest registerModel;
    RegisterModel.RegisterRequest.Meta metaModel;

    public static void start(Context context) {
        Intent signUpIntent = new Intent();
        signUpIntent.setClass(context, RegisterActivity.class);
        context.startActivity(signUpIntent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideRegisterSubComponent(new RegisterModule()).inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onAttach(this);
        tvTitle.setText(getResources().getText(R.string.tv_register));
        btnShowHidePassword.setImageResource(R.drawable.ic_white_eye);
        presenter.getStates();
        registerModel = new RegisterModel.RegisterRequest();
        metaModel = new RegisterModel.RegisterRequest.Meta();
    }

    @OnClick(R.id.submit_view_registeration)
    public void onRegisterClicked() {
        registerModel.setFirstName(etFirstName.getText().toString().trim());
        registerModel.setLastName(etLastName.getText().toString().trim());
        registerModel.setEmail(etEmail.getText().toString().trim());
        registerModel.setPassword(etPassword.getText().toString().trim());
        registerModel.setSubscribe(0);
        registerModel.setMeta(metaModel);

        if (presenter.validateFields(registerModel)) {
            presenter.sendRegisterDetails(registerModel);
        }
    }

    @Override
    public void navigateToLoginActivity(RegisterModel.RegisterResponse registerResponse) {
        LoginActivity.start(this);
    }

    @Override
    public void setUpStatesSpinner(final TaxonomyModel.TaxonomyResponse taxonomyResponse) {
        statesList.add(getResources().getString(R.string.hint_state));
        for (int i = 0; i < taxonomyResponse.getEmbedded().getTaxonomies().size(); i++) {
            String stateNames = taxonomyResponse.getEmbedded().getTaxonomies().get(i).getName();
            String stateId = taxonomyResponse.getEmbedded().getTaxonomies().get(i).getId().toString();
            statesList.add(stateNames);
            AppLog.d("states:" + stateNames);
            AppLog.d("state Id:" + stateId);
        }
        final SpinnerAdapter statesAdapter = new SpinnerAdapter(this, R.layout.item_spinner, statesList, AppContract.Extras.STATELIST);
        statesAdapter.setDropDownViewResource(R.layout.custom_dropdown);
        spinnerState.setAdapter(statesAdapter);

        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                metaModel.setStateId(taxonomyResponse.getEmbedded().getTaxonomies().get(position).getId().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @OnClick(R.id.submit_view_registeration)
    void showHidePassword() {
        if (etPassword.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            btnShowHidePassword.setImageResource(R.drawable.ic_white_custom_hide);
        } else if (etPassword.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            btnShowHidePassword.setImageResource(R.drawable.ic_white_eye);
        }
    }
}
