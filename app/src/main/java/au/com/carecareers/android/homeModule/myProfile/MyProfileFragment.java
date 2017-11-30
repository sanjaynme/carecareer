package au.com.carecareers.android.homeModule.myProfile;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseFragment;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.homeModule.HomeActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.settingsModule.SettingsActivity;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends BaseFragment {
    @Inject
    SharedPreferenceManager preferenceManager;
    @BindView(R.id.tv_greetings_name)
    TextView tvName;
    @BindView(R.id.iv_profile_image)
    ImageView ivProfile;

    public MyProfileFragment() {
        // Required empty public constructor
    }

    public static MyProfileFragment getInstance() {
        return new MyProfileFragment();
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_my_profile;
    }

    @Override
    public void injectComponent(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void setupToolbar() {
        ((HomeActivity) getActivity()).setToolbarTitle(getString(R.string.title_my_profile));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String fullName = " "
                + preferenceManager.getStringValues(AppContract.Preferences.FIRST_NAME)
                + " " +
                preferenceManager.getStringValues(AppContract.Preferences.LAST_NAME)
                + "!";
        tvName.setText(fullName);

        Picasso.with(getContext())
                .load(preferenceManager.getStringValues(AppContract.Preferences.AVATAR_URL))
                .resize(200, 200)
                .centerCrop()
                .placeholder(R.drawable.ic_profile_placeholder)
                .into(ivProfile);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_my_profile, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings: {
                SettingsActivity.start(getContext());
                transitionActivityOpen();
                break;
            }
        }
        return false;
    }
}
