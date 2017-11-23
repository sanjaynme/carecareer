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
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.homeModule.HomeActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.landing.LandingActivity;
import au.com.carecareers.android.profileModule.profileSetup.ProfileSetupActivity;
import au.com.carecareers.android.utilities.AppLog;
import au.com.carecareers.android.utilities.ViewUtils;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    private ProgressDialog mProgressDialog;
    protected SharedPreferenceManager preferenceManager;

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
    public void showError(ResponseBody errorResponseBody) {
        EbAlertDialog.showAlertDialog(this, getErrorMessage(errorResponseBody));
    }

    private String getErrorMessage(ResponseBody errorResponseBody) {
        try {
            JSONObject jsonObject = new JSONObject(errorResponseBody.string());
            String details = jsonObject.getString("detail");
            String message = jsonObject.getString("messages");

            AppLog.d("message:" + message);

            if (message.length() == 2) {
                AppLog.d("details:" + details);
                return details;
            } else {
                JSONObject msgJsonObject = new JSONObject(message);
                JSONArray passwordArray = msgJsonObject.getJSONArray("email");
                String emailMessage = passwordArray.getString(0);
                AppLog.d("password:" + emailMessage);
                return emailMessage;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public void sendAnalyticsData() {

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
