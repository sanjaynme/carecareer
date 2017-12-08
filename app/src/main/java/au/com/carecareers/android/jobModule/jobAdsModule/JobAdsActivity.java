package au.com.carecareers.android.jobModule.jobAdsModule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.jobModule.filterJobModule.JobFilterActivity;
import au.com.carecareers.android.jobModule.jobAdsModule.adapter.JobAdsAdapter;
import au.com.carecareers.android.jobModule.jobAdsModule.adapter.JobAdsListListener;
import au.com.carecareers.android.jobModule.jobAdsModule.model.JobAdsModel;
import au.com.carecareers.android.jobModule.jobDetailsModule.JobDetailsActivity;
import au.com.carecareers.android.utilities.ViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Nikesh on 11/30/2017.
 */

public class JobAdsActivity extends BaseActivity implements JobAdsListListener, PopupMenu.OnMenuItemClickListener {
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
    private PopupMenu popupMenu;
    @BindView(R.id.recycler_view_job_ads)
    RecyclerView recyclerViewJobAds;
    @Inject
    Gson gson;
    private JobAdsModel.JobAdsResponse jobSearchResponse;
    private List<JobAdsModel.JobAdsResponse.Embedded.Job> jobsList;
    private JobAdsAdapter jobAdsAdapter;
    private String searchKeyword;
    private final static int ONE = 1;
    private final static int TWO = 2;
    private final static int THREE = 3;

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
        ViewUtils.setupUI(findViewById(R.id.activity_job_ads), this);
        if (getIntent().getExtras() != null) {
            jobSearchResponse = new Gson().fromJson(getIntent().getStringExtra(AppContract.Extras.DATA), JobAdsModel.JobAdsResponse.class);
            searchKeyword = getIntent().getStringExtra(AppContract.Extras.SEARCH_KEYWORD);
        }
        jobsList = jobSearchResponse.embedded.jobs;
        setUpJobAdsRecyclerView(jobsList);
        etJobSearch.setText(searchKeyword);

        Context context = new ContextThemeWrapper(this, R.style.PopupMenu);

        popupMenu = new PopupMenu(context, tvSort);
        popupMenu.getMenuInflater().inflate(R.menu.menu_sort, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this);

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
        JobDetailsActivity.start(this, gson.toJson(jobDetailsResponse));
        transitionActivityOpen();
    }


    @Override
    public void onRecyclerViewShareItemClick(String jobAdId) {

    }

    @Override
    public void onRecyclerViewSaveItemClick(String jobAdId) {

    }

    @OnClick(R.id.tv_filters)
    void onFilterClicked() {
        JobFilterActivity.start(this);
        transitionActivityOpen();
    }

    @OnClick(R.id.tv_sort)
    void onSortClicked() {
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case ONE:
                tvSort.setText("ONE");
                break;
            case TWO:
                tvSort.setText("TWO");
                break;
            case THREE:
                tvSort.setText("THREE");
                break;
        }
        return false;
    }

    /* int[] locationOfBtn = new int[2];
        Point pointOfSortBtn = new Point();
        pointOfSortBtn.x = locationOfBtn[0];
        pointOfSortBtn.y = locationOfBtn[1];

        Toast.makeText(this, "point x : " + pointOfSortBtn.x + " Point y : " + pointOfSortBtn.y, Toast.LENGTH_LONG).show();
        if (pointOfSortBtn != null)
            showPopup(pointOfSortBtn);*/
/*
    private void showPopup(Point p) {
        int popupWidth = tvSort.getWidth() * 4;
        int popupHeight = tvSort.getWidth() * 6;
        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_menu, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(this);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);
        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.

        int OFFSET_X = tvSort.getWidth() * 2 - (tvSort.getWidth() / 2);
        int OFFSET_Y = 20;//sortingIv.getHeight();

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x - OFFSET_X, p.y + OFFSET_Y);
//        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x, p.y );// + OFFSET_X, p.y + OFFSET_Y);

    }*/
}
