package au.com.carecareers.android.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import au.com.carecareers.android.base.view.IBaseView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Nischal Manandhar on 13/11/2017.
 */

public abstract class BaseFragment extends Fragment implements IBaseView {
    private Unbinder unbinder;

    public abstract int getLayout();

    public abstract void injectComponent();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showProgressDialog(int message) {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showToastMessage(int message) {

    }

    @Override
    public void showAlertDialog(int message) {

    }

    @Override
    public void showError(int message) {

    }

    @Override
    public void sendAnalyticsData() {

    }
}
