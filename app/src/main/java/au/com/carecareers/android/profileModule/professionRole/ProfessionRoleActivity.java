package au.com.carecareers.android.profileModule.professionRole;

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
import au.com.carecareers.android.profileModule.professionRole.adapter.ProfessionRoleAdapter;
import au.com.carecareers.android.profileModule.professionRole.injection.ProfessionRoleModule;
import au.com.carecareers.android.profileModule.professionRole.model.ProfessionRoleResponse;
import au.com.carecareers.android.utilities.ViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class ProfessionRoleActivity extends BaseActivity implements ProfessionRoleContract.IProfessionRoleView {
    @Inject
    ProfessionRoleContract.IProfessionRolePresenter presenter;
    @Inject
    ProfessionRoleAdapter adapter;
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
    private List<ProfessionRoleResponse.Role> roleList;

    public static void startForResult(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, ProfessionRoleActivity.class);
        intent.putExtra(AppContract.Extras.DATA, data);
        activity.startActivityForResult(intent, AppContract.RequestCode.PROFESSION_ROLE);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_profession_role;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideProfessionRoleSubComponent(new ProfessionRoleModule()).inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setupUI(findViewById(R.id.activity_profession_role), this);
        setupRecyclerView();
        presenter.onAttach(this);
        presenter.loadProfessionsRoles();
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(s.toString().trim());
                if (!s.toString().isEmpty()) {
                    ibCross.setVisibility(View.VISIBLE);
                } else {
                    ibCross.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    private void setupRecyclerView() {
        String extra = getIntent().getStringExtra(AppContract.Extras.DATA);
        roleList = gson.fromJson(extra, new TypeToken<List<ProfessionRoleResponse.Role>>() {
        }.getType());

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter.setListener(() -> {
            if (adapter.getCheckedItems().isEmpty()) {
                menuItemDone.setVisible(false);
            } else {
                menuItemDone.setVisible(true);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.ib_cross)
    public void crossClicked() {
        etSearch.setText(null);
        ibCross.setVisibility(View.GONE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_preferred_location, menu);
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
                intent.putExtra(AppContract.Extras.DATA, gson.toJson(adapter.getCheckedItems()));
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
        tvToolbarTitle.setText(getMessage(R.string.title_profession_role));
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
    public void setList(ProfessionRoleResponse professionRoleResponse) {
        adapter.setProfessionRoleList(professionRoleResponse.getEmbedded().getProfessions());
        if (roleList != null && !roleList.isEmpty()) {
            adapter.persistCheckedList(roleList);
            menuItemDone.setVisible(true);
        } else {
            menuItemDone.setVisible(false);
        }
    }
}
