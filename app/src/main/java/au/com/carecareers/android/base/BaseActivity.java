package au.com.carecareers.android.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.customViews.EbAlertDialog;
import au.com.carecareers.android.utilities.ViewUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    private ProgressDialog mProgressDialog;

    @LayoutRes
    public abstract int getLayout();

    protected abstract void injectComponent();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        injectComponent();
        mProgressDialog = new ProgressDialog(this);
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
    public void sendAnalyticsData() {

    }

    private String getMessage(int message) {
        return getString(message);
    }
}
