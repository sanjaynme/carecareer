package au.com.carecareers.android.homeModule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.homeModule.fragments.ApplicationsFragment;
import au.com.carecareers.android.homeModule.fragments.JobAlertsFragment;
import au.com.carecareers.android.homeModule.fragments.SaveListsFragment;
import au.com.carecareers.android.homeModule.model.HomeContract;
import au.com.carecareers.android.homeModule.myProfile.MyProfileFragment;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.jobSearchModule.SearchFragment;
import butterknife.BindView;

/**
 * Created by Sanjay on 11/20/2017.
 */

public class HomeActivity extends BaseActivity implements HomeContract.IHomeView, BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;

    @BindView(R.id.bottom_nav_bar)
    BottomNavigationView bottomNavigationView;
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
        openSearch();
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        tvTitle.setVisibility(View.VISIBLE);
//        tvTitle.setText(getResources().getText(R.string.tv_menu));
        setToolbarTitle(getResources().getString(R.string.tv_menu));
    }

    @Override
    public void setToolbarTitle(String title) {
        tvToolbarTitle.setText(title);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (getSupportFragmentManager().getBackStackEntryCount() == 0 && currentTabId == item.getItemId()) {

        } else {
            switch (item.getItemId()) {
                case R.id.menu_search:
                    currentTabId = item.getItemId();
                    openSearch();
                    break;

                case R.id.menu_saved_lists:
                    if (getSupportFragmentManager().getBackStackEntryCount() > 1 || currentTabId != item.getItemId()) {
                        currentTabId = item.getItemId();
                        openMenuSavedLists();
                    }
                    break;

                case R.id.menu_applications:
                    if (getSupportFragmentManager().getBackStackEntryCount() > 1 || currentTabId != item.getItemId()) {
                        currentTabId = item.getItemId();
                        openApplications();
                    }
                    break;

                case R.id.menu_job_alerts:
                    if (getSupportFragmentManager().getBackStackEntryCount() > 1 || currentTabId != item.getItemId()) {
                        currentTabId = item.getItemId();
                        openJobAlerts();
                    }
                    break;
                case R.id.menu_profile:
                    if (getSupportFragmentManager().getBackStackEntryCount() > 1 || currentTabId != item.getItemId()) {
                        currentTabId = item.getItemId();
                        openProfile();
                    }
                    break;
            }
        }
        return true;
    }

    private void openProfile() {
//        SettingsFragment settingsFragment = new SettingsFragment();
//        setFragmentTransition(settingsFragment, "settingsFragment");
       /* SettingsFragment settingsFragment = new SettingsFragment();
        setFragmentTransition(settingsFragment);*/
        MyProfileFragment myProfileFragment = MyProfileFragment.getInstance();
        setFragmentTransition(myProfileFragment,"");
    }


    private void openJobAlerts() {
        JobAlertsFragment jobAlertsFragment = new JobAlertsFragment();
        setFragmentTransition(jobAlertsFragment, "jobAlertsFragment");
    }

    private void openApplications() {
        ApplicationsFragment applicationsFragment = new ApplicationsFragment();
        setFragmentTransition(applicationsFragment, "applicationsFragment");
    }

    public void openMenuSavedLists() {
        SaveListsFragment saveListsFragment = new SaveListsFragment();
        setFragmentTransition(saveListsFragment, "saveListsFragment");
    }

    private void openSearch() {
        SearchFragment searchFragment = new SearchFragment();
        setFragmentTransition(searchFragment, "searchFragment");
    }

    private void setFragmentTransition(Fragment fragment, String fragmentName) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_left);
        ft.replace(R.id.rl_container, fragment, fragmentName);
        ft.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
