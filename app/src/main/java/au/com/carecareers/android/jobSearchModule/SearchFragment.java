package au.com.carecareers.android.jobSearchModule;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseFragment;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.jobSearchModule.injection.SearchModule;
import au.com.carecareers.android.jobSearchModule.model.LocationModel;
import au.com.carecareers.android.loginModule.register.adapter.SpinnerAdapter;
import au.com.carecareers.android.loginModule.getPages.PagesActivity;
import au.com.carecareers.android.utilities.AppLog;
import au.com.carecareers.android.utilities.ViewUtils;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Sanjay on 11/24/2017.
 */

public class SearchFragment extends BaseFragment implements SearchContract.ISearchView {
    @Inject
    SearchPresenter presenter;

    @BindView(R.id.spinner_job_search_progressbar)
    ProgressBar progressBar;

    @BindView(R.id.tv_welcome_user)
    TextView tvWelcomeUser;

    @BindView(R.id.btn_starting_out)
    Button btnStartingOut;
    @BindView(R.id.et_search_keyword)
    EditText etSearchKeyword;

    @BindView(R.id.spinner_locations)
    Spinner spinnerLocations;

    LocationModel.LocationResponse.Embedded.SearchLocation locationModel;

    @Override
    public void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideSearchSubComponent(new SearchModule()).inject(this);
    }

    @Override
    public void setupToolbar() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewUtils.setupUI(view, getActivity());
        setUpWelcome();
        setUpButton();
        presenter.onAttach(this);
        presenter.loadLocations();
//        locationModel = new LocationModel.LocationResponse.Embedded.SearchLocation();
    }

    private void setUpButton() {
        SpannableString spanString = new SpannableString(getResources().getString(R.string.tv_starting_out));
        spanString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textColorPrimary)), 0, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, 12, 0);
        btnStartingOut.setText(spanString);
    }

    private void setUpWelcome() {
        String name = "lkdnaskjfdbasfkjbaskjfbnasff";
        SpannableString nameString = new SpannableString("Welcome, " + name);
        nameString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textColorPrimary)), 15, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        nameString.setSpan(new StyleSpan(Typeface.BOLD), 8, 9 + name.length(), 0);
        SpannableStringBuilder spanString = new SpannableStringBuilder("\n Search for your next career move");
        tvWelcomeUser.setText(TextUtils.concat(nameString, spanString));
    }

    @OnClick(R.id.btn_starting_out)
    void startingOutClicked() {
        PagesActivity.start(getActivity(), AppContract.Page.STARTING_OUT);
        transitionActivityOpen();
    }

    @OnClick(R.id.tv_view_my_fav_search)
    void viewMyFavSearchClicked() {
      /*  SaveListsFragment saveListsFragment = new SaveListsFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.rl_container, saveListsFragment)
                .addToBackStack(null)
                .commit();*/
    }

    @Override
    public void sendCountryData(LocationModel.LocationResponse locationResponse) {
        progressBar.setVisibility(View.GONE);
        spinnerLocations.setVisibility(View.VISIBLE);
        ArrayList<String> countryList = new ArrayList<>();
        for (int i = 0; i < locationResponse.getEmbedded().getLocations().size(); i++) {
            String countryNames = locationResponse.getEmbedded().getLocations().get(i).getName();
            String countryId = locationResponse.getEmbedded().getLocations().get(i).getId().toString();
            countryList.add(countryNames);
            AppLog.d("country:" + countryNames);
            AppLog.d("country Id:" + countryId);
        }
        final SpinnerAdapter statesAdapter = new SpinnerAdapter(getActivity(), R.layout.item_spinner, countryList, AppContract.Extras.COUNTRYLIST);
        statesAdapter.setDropDownViewResource(R.layout.custom_dropdown);
        spinnerLocations.setAdapter(statesAdapter);

        spinnerLocations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String countryNames = locationResponse.getEmbedded().getLocations().get(position).getName();
                int countryId = locationResponse.getEmbedded().getLocations().get(position).getId();
                AppLog.d("position:" + position);
                AppLog.d("country selected name::::" + countryNames);
                AppLog.d("country selected id:::::" + countryId);
//                locationModel.setId(countryId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void navigateToJobAds(LocationModel.LocationResponse locationResponse) {
        JobAdsActivity.start(getActivity());
        transitionActivityOpen();
    }

    @OnClick(R.id.btn_job_search)
    void onJobSearchClicked() {
    /*    String keywords = etSearchKeyword.getText().toString().trim();
        int locationId = locationModel.getId();
        presenter.searchJobs(keywords, locationId);*/
        Toast.makeText(getActivity(), "Search", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }
}
