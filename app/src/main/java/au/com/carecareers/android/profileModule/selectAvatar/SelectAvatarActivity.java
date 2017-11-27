package au.com.carecareers.android.profileModule.selectAvatar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.customViews.EbProgressView;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.profileModule.selectAvatar.adapter.GridSpacingItemDecoration;
import au.com.carecareers.android.profileModule.selectAvatar.adapter.SelectAvatarAdapter;
import au.com.carecareers.android.profileModule.selectAvatar.injection.EndlessRecyclerViewScrollListener;
import au.com.carecareers.android.profileModule.selectAvatar.injection.SelectAvatarModule;
import au.com.carecareers.android.profileModule.selectAvatar.model.AvatarResponse;
import butterknife.BindView;

public class SelectAvatarActivity extends BaseActivity implements SelectAvatarContract.ISelectAvatarView {
    @Inject
    SelectAvatarContract.ISelectAvatarPresenter presenter;
    @Inject
    SelectAvatarAdapter avatarAdapter;
    @Inject
    Gson gson;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.eb_progress_view)
    EbProgressView ebProgressView;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SelectAvatarActivity.class);
        context.startActivity(intent);
    }

    public static void startForResult(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, SelectAvatarActivity.class);
        activity.startActivityForResult(intent, AppContract.RequestCode.SELECT_AVATAR);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_select_avatar;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideSelectAvatarSubComponent(new SelectAvatarModule()).inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onAttach(this);
        presenter.loadAvatarList(1, avatarAdapter.getItemCount() > 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolbarTitle.setText(getMessage(R.string.title_select_avatar));
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
    public void setupAvatarList(AvatarResponse avatarResponse) {
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        avatarAdapter.setListener(avatar -> {
            Intent intent = new Intent();
            intent.putExtra(AppContract.Extras.DATA, gson.toJson(avatar));
            setResult(RESULT_OK, intent);
            onBackPressed();
        });
        avatarAdapter.setAvatarList(avatarResponse.getEmbedded().getAvatars());
        recyclerView.setAdapter(avatarAdapter);
        recyclerView.setLayoutManager(manager);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.dimen_16dp);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, spacingInPixels, true));
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (page < avatarResponse.getPageCount()) {
                    presenter.loadAvatarList(page, avatarAdapter.getItemCount() > 0);
                }
            }
        });

    }

    @Override
    public void addMoreAvatars(AvatarResponse avatarResponse) {
        avatarAdapter.addMoreAvatars(avatarResponse.getEmbedded().getAvatars());
        recyclerView.scrollToPosition(avatarAdapter.getItemCount() - 1);
    }
}
