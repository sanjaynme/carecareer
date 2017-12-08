package au.com.carecareers.android.jobModule.filterJobModule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.jobModule.jobSearchModule.model.LocationModel;
import au.com.carecareers.android.profileModule.locationArea.LocationAreaActivity;
import au.com.carecareers.android.profileModule.locationArea.model.LocationAreaResponse;
import au.com.carecareers.android.utilities.ViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Nikesh on 12/8/2017.
 */

public class JobFilterActivity extends BaseActivity {
    @BindView(R.id.job_filter_toolbar)
    Toolbar toolbar;
    @Inject
    Gson gson;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;

    @BindView(R.id.tv_filter_location_area)
    TextView tvLocationArea;
    @BindView(R.id.tv_filter_profession_role)
    TextView tvProfessionRole;
    @BindView(R.id.tv_filter_work_type)
    TextView tvWorkType;
    @BindView(R.id.tv_filter_salary)
    TextView tvSalaryType;
    private LocationModel.LocationResponse.Embedded.SearchLocation searchLocationModel;
    private List<LocationAreaResponse.Area> areaList;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, JobFilterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_job_filter;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setupUI(findViewById(R.id.rl_activity_job_filter), this);
        searchLocationModel = new LocationModel.LocationResponse.Embedded.SearchLocation();
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolbarTitle.setText(getMessage(R.string.tv_title_select_filter));
    }

    @OnClick(R.id.ll_filter_location_area)
    public void locationAreaClicked() {
        LocationAreaActivity.startForResult(this, gson.toJson(areaList));
        transitionActivityOpen();
    }

    @OnClick(R.id.ll_filter_profession_role)
    public void professionRoleClicked() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == AppContract.RequestCode.LOCATION_AREA) {
                String extra = data.getStringExtra(AppContract.Extras.DATA);
                areaList = gson.fromJson(extra, new TypeToken<List<LocationAreaResponse.Area>>() {
                }.getType());
                LocationModel.LocationResponse.Embedded.SearchLocation searchLocationModel = new LocationModel.LocationResponse.Embedded.SearchLocation();
                this.searchLocationModel.id = searchLocationModel.id;
            } else if (requestCode == AppContract.RequestCode.PROFESSION_ROLE) {

            }


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_done:
                /*if (candidateModel.address == null) {
                    showAlertDialog(R.string.err_no_location_selected);
                } else {
                }*/
                onBackPressed();
                break;
        }
        return false;
    }

}
