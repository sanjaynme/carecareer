package au.com.carecareers.android.base.view;

import android.support.annotation.StringRes;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public interface IBaseView {
    void showProgressDialog(@StringRes int message);

    void hideProgressDialog();

    void showToastMessage(@StringRes int message);

    void showAlertDialog(@StringRes int message);

    void showError(@StringRes int message);

    void sendAnalyticsData();
}
