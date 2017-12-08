package au.com.carecareers.android.jobModule.jobAdsModule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.jobModule.jobAdsModule.adapter.JobAdsAdapter;
import au.com.carecareers.android.jobModule.jobAdsModule.adapter.JobAdsListListener;
import au.com.carecareers.android.jobModule.jobAdsModule.model.JobAdsModel;
import au.com.carecareers.android.jobModule.jobDetailsModule.JobDetailsActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Nikesh on 11/30/2017.
 */

public class JobAdsActivity extends BaseActivity implements JobAdsListListener {
    @BindView(R.id.iv_back_arrow)
    ImageView ivBackArrow;
    @BindView(R.id.et_job_search)
    EditText etJobSearch;
    @BindView(R.id.tv_filters)
    TextView tvFilter;
    @BindView(R.id.tv_results)
    TextView tvResults;
    @BindView(R.id.tv_save_search)
    TextView tvSaveSearch;
    @BindView(R.id.tv_sort)
    TextView tvSort;

    @BindView(R.id.recycler_view_job_ads)
    RecyclerView recyclerViewJobAds;

    private JobAdsModel.JobAdsResponse jobSearchResponse;
    private List<JobAdsModel.JobAdsResponse.Embedded.Job> jobsList;
    private JobAdsAdapter jobAdsAdapter;
    private String searchKeyword;

    public static void start(Context context, String searchkeyWords, String extraData) {
        Intent intent = new Intent();
        intent.setClass(context, JobAdsActivity.class);
        intent.putExtra(AppContract.Extras.DATA, extraData);
        intent.putExtra(AppContract.Extras.SEARCH_KEYWORD, searchkeyWords);
        context.startActivity(intent);
    }

    @Override
    public void setupToolbar() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_job_ads;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras() != null) {
            jobSearchResponse = new Gson().fromJson(getIntent().getStringExtra(AppContract.Extras.DATA), JobAdsModel.JobAdsResponse.class);
            searchKeyword = getIntent().getStringExtra(AppContract.Extras.SEARCH_KEYWORD);
        }
        jobsList = jobSearchResponse.embedded.jobs;
        setUpJobAdsRecyclerView(jobsList);
        etJobSearch.setText(searchKeyword);
    }

    private void setUpJobAdsRecyclerView(List<JobAdsModel.JobAdsResponse.Embedded.Job> jobSearchResponse) {
        jobAdsAdapter = new JobAdsAdapter(JobAdsActivity.this, jobSearchResponse, this);
        recyclerViewJobAds.setAdapter(jobAdsAdapter);
        recyclerViewJobAds.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.iv_back_arrow)
    void onBackArrowClicked() {
        super.onBackPressed();
    }

    @Override
    public void onRecyclerViewDetailsItemClick(JobAdsModel.JobAdsResponse.Embedded.Job jobDetailsResponse) {
        Gson gson = new Gson();
        JobDetailsActivity.start(this, gson.toJson(jobDetailsResponse));
        transitionActivityOpen();
    }


    @Override
    public void onRecyclerViewShareItemClick(String jobAdId) {

    }

    @Override
    public void onRecyclerViewSaveItemClick(String jobAdId) {

    }
}
