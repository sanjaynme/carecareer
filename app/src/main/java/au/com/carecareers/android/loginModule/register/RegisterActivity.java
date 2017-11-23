package au.com.carecareers.android.loginModule.register;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
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
import au.com.carecareers.android.loginModule.termsAndCondition.TermsAndConditionActivity;
import au.com.carecareers.android.utilities.AppLog;
import au.com.carecareers.android.utilities.ViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Nikesh on 11/15/2017.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.IRegisterView {
    @BindView(R.id.spinner_progressbar)
    ProgressBar progressBar;

    @BindView(R.id.sign_up_toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @BindView(R.id.et_email)
    EditText etEmail;

    @BindView(R.id.et_firstname)
    EditText etFirstName;

    @BindView(R.id.et_lastname)
    EditText etLastName;

    @BindView(R.id.et_sign_up_password)
    EditText etPassword;

    @BindView(R.id.spinner_state)
    Spinner spinnerState;

    @Inject
    RegisterPresenter presenter;

    @BindView(R.id.btn_show_hide_register_password)
    ImageButton btnShowHidePassword;

    RegisterModel.RegisterRequest registerModel;
    RegisterModel.RegisterRequest.Meta metaModel;
    private ArrayList<String> statesList;

    public static void start(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideRegisterSubComponent(new RegisterModule()).inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setupUI(findViewById(R.id.activity_register), this);

        presenter.onAttach(this);
        btnShowHidePassword.setImageResource(R.drawable.ic_eye);
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

        int selectedItemOfMySpinner = spinnerState.getSelectedItemPosition();

        if (presenter.validateFields(registerModel)) {
            if (selectedItemOfMySpinner == 0) {
                presenter.validateSpinner(selectedItemOfMySpinner);
            } else {
                presenter.sendRegisterDetails(registerModel);
            }
        }
    }

    @Override
    public void navigateToLoginActivity(RegisterModel.RegisterResponse registerResponse) {
        etFirstName.getText().clear();
        etLastName.getText().clear();
        etEmail.getText().clear();
        etPassword.getText().clear();
        spinnerState.setSelection(0);
        showRegisterSuccessMessageDialog("Registeration has been done successfully.");
    }

    private void showRegisterSuccessMessageDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle("Care Career");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                hideProgressDialog();
                finish();
                LoginActivity.start(RegisterActivity.this);
                transitionActivityOpen();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void setUpStatesSpinner(final TaxonomyModel.TaxonomyResponse taxonomyResponse) {
        statesList = new ArrayList<>();
        progressBar.setVisibility(View.GONE);
        spinnerState.setVisibility(View.VISIBLE);
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
                String name = taxonomyResponse.getEmbedded().getTaxonomies().get(position).getName();
                int id = taxonomyResponse.getEmbedded().getTaxonomies().get(position).getId();
                AppLog.d("position:" + position);
                AppLog.d("state name::::" + name);
                AppLog.d("state id:::::" + id);
                metaModel.setStateId(String.valueOf(id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    @OnClick(R.id.btn_show_hide_register_password)
    void showHidePassword() {
        if (etPassword.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            btnShowHidePassword.setImageResource(R.drawable.ic_eye_slash);
        } else if (etPassword.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            btnShowHidePassword.setImageResource(R.drawable.ic_eye);
        }
    }

    @OnClick(R.id.tv_terms_and_conditions)
    void termsAndConditionsClicked() {
        TermsAndConditionActivity.start(RegisterActivity.this);
        transitionActivityOpen();
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

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvTitle.setText(getResources().getText(R.string.tv_register));
    }
}
