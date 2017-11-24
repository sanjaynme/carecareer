package au.com.carecareers.android.profileModule.selectAvatar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.profileModule.selectAvatar.adapter.GridSpacingItemDecoration;
import au.com.carecareers.android.profileModule.selectAvatar.adapter.SelectAvatarAdapter;
import au.com.carecareers.android.profileModule.selectAvatar.injection.SelectAvatarModule;
import butterknife.BindView;

public class SelectAvatarActivity extends BaseActivity {
    @Inject
    SelectAvatarContract.ISelectAvatarPresenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    SelectAvatarAdapter avatarAdapter;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SelectAvatarActivity.class);
        context.startActivity(intent);
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
        avatarAdapter = new SelectAvatarAdapter();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setAdapter(avatarAdapter);
        recyclerView.setLayoutManager(manager);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.dimen_25dp);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true));
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolbarTitle.setText(getMessage(R.string.title_select_avatar));
    }
}
