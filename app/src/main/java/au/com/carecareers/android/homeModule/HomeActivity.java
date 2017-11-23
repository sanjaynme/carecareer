package au.com.carecareers.android.homeModule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.homeModule.model.HomeContract;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.login.model.LoginModel;
import butterknife.BindView;

/**
 * Created by Nikesh on 11/20/2017.
 */

public class HomeActivity extends BaseActivity implements HomeContract.IHomeView, BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.home_toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;

    @BindView(R.id.bottom_nav_bar)
    BottomNavigationView bottomNavigationView;

    private LoginModel.LoginRespones loginResponses;
    private static int currentTabId;

    public static void start(Context context) {
        Intent homeIntent = new Intent();
        homeIntent.setClass(context, HomeActivity.class);
        context.startActivity(homeIntent);
    }


    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        currentTabId = bottomNavigationView.getSelectedItemId();
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
        tvTitle.setText(getResources().getText(R.string.tv_home));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0 && currentTabId == item.getItemId()) {

        } else {
            switch (item.getItemId()) {
                case R.id.menu_search:
                    currentTabId = item.getItemId();
                    break;

                case R.id.menu_saved_lists:
                    if (getSupportFragmentManager().getBackStackEntryCount() > 1 || currentTabId != item.getItemId()) {
                        currentTabId = item.getItemId();
                    }
                    break;

                case R.id.menu_applied_jobs:
                    if (getSupportFragmentManager().getBackStackEntryCount() > 1 || currentTabId != item.getItemId()) {
                        currentTabId = item.getItemId();
                    }
                    break;

                case R.id.menu_job_alerts:
                    if (getSupportFragmentManager().getBackStackEntryCount() > 1 || currentTabId != item.getItemId()) {
                        currentTabId = item.getItemId();
                    }
                    break;
                case R.id.menu_profile:
                    if (getSupportFragmentManager().getBackStackEntryCount() > 1 || currentTabId != item.getItemId()) {
                        currentTabId = item.getItemId();
                    }
                    break;
            }
        }
        return true;
    }
}
