package au.com.carecareers.android.base;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import au.com.carecareers.android.R;
import au.com.carecareers.android.application.CareCareerApp;
import au.com.carecareers.android.base.view.IBaseView;
import au.com.carecareers.android.customViews.EbAlertDialog;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.utilities.ViewUtils;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public abstract class BaseFragment extends Fragment implements IBaseView {
    private ProgressDialog mProgressDialog;

    private Unbinder unbinder;

    public abstract int getLayout();

    public abstract void injectComponent(BaseComponent baseComponent);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true

        );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        injectComponent(((CareCareerApp) getActivity().getApplication()).getBaseComponent());
        unbinder = ButterKnife.bind(this, view);
        setupToolbar();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (!getActivity().isDestroyed() && !getActivity().isFinishing() && mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
    }

    @Override
    public void showToastMessage(int message) {
        ViewUtils.showToastMessage(getActivity(), getMessage(message));
    }

    protected String getMessage(@StringRes int message) {
        return getString(message);
    }

    @Override
    public void showAlertDialog(int message) {
        EbAlertDialog.showAlertDialog(getContext(), getMessage(message));
    }

    @Override
    public void showAlertDialog(String message) {
        EbAlertDialog.showAlertDialog(getContext(), message);
    }

    @Override
    public void showAlertDialogFinishActivity(int message) {
        EbAlertDialog.showAlertDialogWithCallback(getContext(), getMessage(message), new EbAlertDialog.ConfirmationDialogCallback() {
            @Override
            public void onOkClicked() {
                getActivity().onBackPressed();
            }

            @Override
            public void onCancelClicked() {

            }
        });
    }

    @Override
    public void showAlertDialogFinishActivity(String message) {
        EbAlertDialog.showAlertDialogWithCallback(getContext(), message, new EbAlertDialog.ConfirmationDialogCallback() {
            @Override
            public void onOkClicked() {
                getActivity().onBackPressed();
            }

            @Override
            public void onCancelClicked() {

            }
        });
    }

    @Override
    public void showError(ResponseBody errorResponseBody, int errorType) {
        EbAlertDialog.showAlertDialog(getActivity(), getErrorMessage(errorResponseBody, errorType));
    }

    private String getErrorMessage(ResponseBody errorResponseBody, int errorType) {
        return null;
    }

    @Override
    public void sendAnalyticsData() {

    }

    @Override
    public void setupToolbar() {

    }

    protected void transitionBackPressed() {
        getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    protected void transitionFadeOut() {
        getActivity().overridePendingTransition(0, R.anim.fade_out);
    }

    protected void transitionActivityOpen() {
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
