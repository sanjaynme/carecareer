package au.com.carecareers.android.profileModule.locationArea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.customViews.EbProgressView;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.profileModule.locationArea.adapter.LocationAreaAdapter;
import au.com.carecareers.android.profileModule.locationArea.injection.LocationAreaModule;
import au.com.carecareers.android.profileModule.locationArea.model.LocationAreaResponse;
import au.com.carecareers.android.utilities.ViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class LocationAreaActivity extends BaseActivity implements LocationAreaContract.ILocationAreaView {
    @Inject
    LocationAreaAdapter locationAreaAdapter;
    @Inject
    LocationAreaContract.ILocationAreaPresenter presenter;
    @Inject
    Gson gson;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.ib_cross)
    ImageButton ibCross;
    @BindView(R.id.eb_progress_view)
    EbProgressView ebProgressView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private MenuItem menuItemDone;
    private int totalPage;
    private List<LocationAreaResponse.Area> listArea;

    public static void startForResult(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, LocationAreaActivity.class);
        intent.putExtra(AppContract.Extras.DATA, data);
        activity.startActivityForResult(intent, AppContract.RequestCode.LOCATION_AREA);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_location_area;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.locationAreaSubComponent(new LocationAreaModule()).inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setupUI(findViewById(R.id.activity_location_area), this);
        presenter.onAttach(this);
        setupRecyclerView();
        presenter.loadLocationWithoutPagination();
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                locationAreaAdapter.getFilter().filter(s.toString().trim());
                if (!s.toString().isEmpty()) {
                    ibCross.setVisibility(View.VISIBLE);
                } else {
                    ibCross.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setupRecyclerView() {
        String extra = getIntent().getStringExtra(AppContract.Extras.DATA);
        listArea = gson.fromJson(extra, new TypeToken<List<LocationAreaResponse.Area>>() {
        }.getType());

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        locationAreaAdapter.setListener(() -> {
            if (locationAreaAdapter.getCheckedItems().isEmpty()) {
                menuItemDone.setVisible(false);
            } else {
                menuItemDone.setVisible(true);
            }
        });
        recyclerView.setAdapter(locationAreaAdapter);

        /*Remove pagination as per requirement*/
       /* recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (page <= totalPage && etSearch.getText().toString().isEmpty()) {
                    presenter.loadLocationArea(page, locationAreaAdapter.getItemCount() > 0);
                }
            }
        });*/
    }

    @OnClick(R.id.ib_cross)
    public void crossClicked() {
        etSearch.setText(null);
        ibCross.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        menuItemDone = menu.findItem(R.id.menu_done);
        menuItemDone.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_done:
                Intent intent = new Intent();
                intent.putExtra(AppContract.Extras.DATA, gson.toJson(locationAreaAdapter.getCheckedItems()));
                setResult(RESULT_OK, intent);
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolbarTitle.setText(getMessage(R.string.title_location_area));
    }

    @Override
    public void showProgressBar() {
        ebProgressView.showProgress();
    }

    @Override
    public void hideProgressBar() {
        ebProgressView.hideProgress();
    }

    @Override
    public void showFooterProgress() {
        // locationAreaAdapter.showFooterProgress();
    }

    @Override
    public void hideFooterProgress() {
        //locationAreaAdapter.removeFooterProgress();
    }

    @Override
    public void setErrorMessage(int message) {
        ebProgressView.setMessage(getMessage(message));
    }

    @Override
    public void hideErrorMessage() {
        ebProgressView.hideMessage();
    }

    @Override
    public void showRecyclerView() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRecyclerView() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void setList(LocationAreaResponse locationAreaResponse) {
        totalPage = locationAreaResponse.getPageCount();
        locationAreaAdapter.setListLocation(locationAreaResponse.getEmbedded().getLocations());
        if (listArea != null && !listArea.isEmpty()) {
            locationAreaAdapter.persistCheckedList(listArea);
            menuItemDone.setVisible(true);
        } else {
            menuItemDone.setVisible(false);
        }
    }

    @Override
    public void addMoreItems(LocationAreaResponse locationAreaResponse) {
        locationAreaAdapter.addMoreItems(locationAreaResponse.getEmbedded().getLocations());
    }
}
