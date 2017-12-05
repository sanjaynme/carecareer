package au.com.carecareers.android.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.application.CareCareerApp;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.customViews.EbAlertDialog;
import au.com.carecareers.android.homeModule.HomeActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.landing.LandingActivity;
import au.com.carecareers.android.profileModule.profileSetup.ProfileSetupActivity;
import au.com.carecareers.android.utilities.AppLog;
import au.com.carecareers.android.utilities.ViewUtils;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static au.com.carecareers.android.contracts.AppContract.ErrorTypes.CHANGE_PASSWORD;
import static au.com.carecareers.android.contracts.AppContract.ErrorTypes.FORGOT_PASSWORD;
import static au.com.carecareers.android.contracts.AppContract.ErrorTypes.LOGIN;
import static au.com.carecareers.android.contracts.AppContract.ErrorTypes.REGISTER;
import static au.com.carecareers.android.contracts.AppContract.ErrorTypes.SELECT_AVATAR;
import static au.com.carecareers.android.contracts.AppContract.ErrorTypes.TERMS_AND_CONDITIONS;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    private ProgressDialog mProgressDialog;
    private String errorMessage;

    @LayoutRes
    protected abstract int getLayout();

    protected abstract void injectComponent(BaseComponent baseComponent);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        injectComponent(((CareCareerApp) getApplication()).getBaseComponent());
        ButterKnife.bind(this);
        mProgressDialog = new ProgressDialog(this);
        setupToolbar();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!(this instanceof LandingActivity ||
                this instanceof ProfileSetupActivity ||
                this instanceof HomeActivity)) {
            transitionBackPressed();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void showProgressDialog(int message) {
        if (mProgressDialog != null) {
            hideProgressDialog();
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setTitle(getMessage(R.string.app_name));
            mProgressDialog.setMessage(getMessage(message));
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void showToastMessage(int message) {
        ViewUtils.showToastMessage(this, getMessage(message));
    }

    @Override
    public void showAlertDialog(int message) {
        EbAlertDialog.showAlertDialog(this, getMessage(message));
    }

    @Override
    public void showAlertDialog(String message) {
        EbAlertDialog.showAlertDialog(this, message);
    }

    @Override
    public void showAlertDialogFinishActivity(int message) {
        EbAlertDialog.showAlertDialogWithCallback(this, getMessage(message), new EbAlertDialog.ConfirmationDialogCallback() {
            @Override
            public void onOkClicked() {
                onBackPressed();
            }

            @Override
            public void onCancelClicked() {

            }
        });
    }

    @Override
    public void showAlertDialogFinishActivity(String message) {
        EbAlertDialog.showAlertDialogWithCallback(this, message, new EbAlertDialog.ConfirmationDialogCallback() {
            @Override
            public void onOkClicked() {
                onBackPressed();
            }

            @Override
            public void onCancelClicked() {

            }
        });
    }


    @Override
    public void showError(ResponseBody errorResponseBody, int errorType) {
        EbAlertDialog.showAlertDialog(this, getErrorMessage(errorResponseBody, errorType));
    }

    private String getErrorMessage(ResponseBody errorResponseBody, int errorType) {
        switch (errorType) {
            case REGISTER:
                try {
                    JSONObject jsonObject = new JSONObject(errorResponseBody.string());
                    String message = jsonObject.getString("messages");

                    JSONObject msgJsonObject = new JSONObject(message);
                    JSONArray passwordArray = msgJsonObject.getJSONArray("email");
                    errorMessage = passwordArray.getString(0);
                    AppLog.d("password:" + errorMessage);
                } catch (Exception e) {
                    return e.getMessage();
                }
                break;

            case LOGIN:
                try {
                    JSONObject jsonObject = new JSONObject(errorResponseBody.string());
                    errorMessage = jsonObject.getString("detail");
                    AppLog.d("details:" + errorMessage);
                } catch (Exception e) {
                    return e.getMessage();
                }
                break;

            case FORGOT_PASSWORD:
                break;

            case TERMS_AND_CONDITIONS:
                break;

            case CHANGE_PASSWORD:
                try {
                    JSONObject jsonObject = new JSONObject(errorResponseBody.string());
                    String message = jsonObject.getString("messages");

                    JSONObject msgJsonObject = new JSONObject(message);
                    JSONArray passwordArray = msgJsonObject.getJSONArray("current_password");
                    errorMessage = passwordArray.getString(0);
                    AppLog.d("currentPassword:" + errorMessage);
                } catch (Exception e) {
                    return e.getMessage();
                }
                break;
            case SELECT_AVATAR:
                break;
        }
        return errorMessage;
    }

    @Override
    public void sendAnalyticsData() {

    }

    @Override
    public void setupToolbar() {

    }

    protected String getMessage(@StringRes int message) {
        return getString(message);
    }

    protected void transitionBackPressed() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    protected void transitionFadeOut() {
        overridePendingTransition(0, R.anim.fade_out);
    }

    protected void transitionActivityOpen() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
