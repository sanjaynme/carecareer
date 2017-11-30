package au.com.carecareers.android.profileModule.profileSetup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.customViews.EbImageHelperFragment;
import au.com.carecareers.android.customViews.SubmitView;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.homeModule.HomeActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.profileModule.locationArea.LocationAreaActivity;
import au.com.carecareers.android.profileModule.locationArea.model.LocationAreaResponse;
import au.com.carecareers.android.profileModule.model.CandidateModel;
import au.com.carecareers.android.profileModule.preferredLocation.PreferredLocationActivity;
import au.com.carecareers.android.profileModule.profileSetup.injection.ProfileSetupModule;
import au.com.carecareers.android.profileModule.selectAvatar.SelectAvatarActivity;
import au.com.carecareers.android.profileModule.selectAvatar.model.AvatarResponse;
import au.com.carecareers.android.utilities.ViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class ProfileSetupActivity extends BaseActivity implements ProfileSetupContract.IProfileSetupView {
    @Inject
    Gson gson;
    @Inject
    ProfileSetupContract.IProfileSetupPresenter presenter;
    @Inject
    SharedPreferenceManager preferenceManager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.scroll_view_main)
    ScrollView scrollView;
    @BindView(R.id.iv_profile_image)
    ImageView ivProfile;
    @BindView(R.id.pb_profile_image)
    ProgressBar pbProfileImage;
    @BindView(R.id.tv_preferred_location)
    TextView tvPreferredLocation;
    @BindView(R.id.tv_location_area)
    TextView tvLocationArea;
    @BindView(R.id.et_what_is_your_career_move)
    EditText etCareerMove;
    @BindView(R.id.submit_view)
    SubmitView submitView;

    private CandidateModel candidateModel;
    private EbImageHelperFragment ebImageHelperFragment;
    private List<LocationAreaResponse.Area> listArea;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ProfileSetupActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_profile_setup;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideProfileSetupSubComponent(new ProfileSetupModule()).inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setupUI(findViewById(R.id.activity_profile_setup), this);
        this.candidateModel = new CandidateModel();
        presenter.onAttach(this);

        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        //Todo Fix scroll issue with scrollbar and edittext
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (etCareerMove.hasFocus()) {
                    etCareerMove.clearFocus();
                }

                return false;
            }
        });
        initViews();
        initImageHelperFragment();
    }

    private void initViews() {
        submitView.setOnClickListener(new SubmitView.SubmitViewClickListener() {
            @Override
            public void onSubmitClicked(View view) {

            }

            @Override
            public void onSkipClicked(View view) {
                preferenceManager.setKeyValues(AppContract.Preferences.IS_PROFILE_COMPLETE, true);
                HomeActivity.start(ProfileSetupActivity.this);
                finish();
                transitionActivityOpen();
            }

            @Override
            public void onNextClicked(View view) {
                preferenceManager.setKeyValues(AppContract.Preferences.IS_PROFILE_COMPLETE, true);
                HomeActivity.start(ProfileSetupActivity.this);
                finish();
                transitionActivityOpen();
            }
        });
    }

    private void initImageHelperFragment() {
        ebImageHelperFragment = EbImageHelperFragment.newInstance(false, false);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(ebImageHelperFragment, EbImageHelperFragment.TAG);
        transaction.commit();
        ebImageHelperFragment.setListener(new EbImageHelperFragment.EbImageHelperListener() {
            @Override
            public void onImageSuccess(String imagePath) {
                Log.d("path", "onImageSuccess: " + imagePath);
                presenter.uploadImage(imagePath);
                setProfileImage(imagePath, false);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == AppContract.RequestCode.PREFERRED_LOCATION) {
                String extra = data.getStringExtra(AppContract.Extras.DATA);
                CandidateModel candidateModel = gson.fromJson(extra, CandidateModel.class);
                this.candidateModel.address = candidateModel.address;
                if (this.candidateModel.address != null) {
                    setPreferredLocation(this.candidateModel.address.address);
                } else {
                    setPreferredLocation("");
                }
            } else if (requestCode == AppContract.RequestCode.SELECT_AVATAR) {
                String extra = data.getStringExtra(AppContract.Extras.DATA);
                AvatarResponse.Avatar avatar = gson.fromJson(extra, AvatarResponse.Avatar.class);
                presenter.setAvatar(avatar);
                setProfileImage(avatar.getLinks().getUrl().getHref(), true);
            } else if (requestCode == AppContract.RequestCode.LOCATION_AREA) {
                String extra = data.getStringExtra(AppContract.Extras.DATA);
                listArea = gson.fromJson(extra, new TypeToken<List<LocationAreaResponse.Area>>() {
                }.getType());
                if (listArea.size() > 1) {
                    setLocationArea(listArea.get(0).getName() + " and " + (listArea.size() - 1) + " more");
                } else {
                    setLocationArea(listArea.get(0).getName());
                }
            }
        }
    }

    @OnClick(R.id.tv_upload_photo)
    public void uploadPhotoClicked() {
        ebImageHelperFragment.showBottomSheetFragment();
    }

    @OnClick(R.id.tv_select_avatar)
    public void selectAvatar() {
        SelectAvatarActivity.startForResult(this);
        transitionActivityOpen();
    }

    @OnClick(R.id.ll_preferred_location_main)
    public void preferredLocationClicked() {
        PreferredLocationActivity.startForResult(this, gson.toJson(candidateModel));
        transitionActivityOpen();
    }

    @OnClick(R.id.ll_location_area)
    public void locationAreaClicked() {
        LocationAreaActivity.startForResult(this, gson.toJson(listArea));
        transitionActivityOpen();
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolbarTitle.setText(getMessage(R.string.title_personal_details));
    }

    @Override
    public void setProfileImage(String imagePath, boolean isAvatar) {
        if (isAvatar) {
            Picasso.with(ProfileSetupActivity.this)
                    .load(imagePath)
                    .resize(200, 200)
                    .centerCrop()
                    .into(ivProfile);
        } else {
            Picasso.with(ProfileSetupActivity.this)
                    .load(new File(imagePath))
                    .resize(200, 200)
                    .centerCrop()
                    .into(ivProfile);
        }
    }

    @Override
    public void showProfileProgress() {
        pbProfileImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProfileProgress() {
        pbProfileImage.setVisibility(View.GONE);
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {

    }

    @Override
    public void setPreferredLocation(String location) {
        tvPreferredLocation.setText(location);
    }

    @Override
    public void setLocationArea(String locationArea) {
        tvLocationArea.setText(locationArea);
    }

    @Override
    public void setProfessionRole(String professionRole) {

    }

    @Override
    public void setWorkType(String workType) {

    }
}
