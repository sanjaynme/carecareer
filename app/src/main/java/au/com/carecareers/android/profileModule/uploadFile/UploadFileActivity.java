package au.com.carecareers.android.profileModule.uploadFile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.profileModule.uploadFile.injection.UploadFileModule;
import au.com.carecareers.android.profileModule.uploadFile.model.UploadFileModel;
import au.com.carecareers.android.utilities.FileUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class UploadFileActivity extends BaseActivity implements UploadFileContract.IUploadFileView {
    @Inject
    UploadFileContract.IUploadFilePresenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;

    @BindView(R.id.sp_choose_type)
    Spinner spChooseType;
    private String filePath;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, UploadFileActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_upload_file;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.uploadFileSubComponent(new UploadFileModule()).inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onAttach(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    private void initView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_preferred_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
            case R.id.menu_done: {
                UploadFileModel.Request request = new UploadFileModel.Request();
                request.setFilePath(filePath);
                request.setType(spChooseType.getSelectedItemPosition());
                presenter.doneClicked(request);
                break;
            }

        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case AppContract.RequestCode.FILES:
                    filePath = FileUtils.getPath(this, data.getData());
                    break;
            }
        }
    }

    @OnClick(R.id.tv_select_file)
    public void selectFile() {
        Intent intent = new Intent();
        intent.setType("*/*");
        String[] mimetypes = {"application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/msword", "application/pdf", "file/txt"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.tv_select_file)), AppContract.RequestCode.FILES);
    }


    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolbarTitle.setText(getString(R.string.title_upload_file));
    }
}
