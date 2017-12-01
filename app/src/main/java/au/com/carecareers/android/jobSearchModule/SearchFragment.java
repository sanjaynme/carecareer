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

import com.google.gson.Gson;

import java.util.ArrayList;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseFragment;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.jobAdsModule.JobAdsActivity;
import au.com.carecareers.android.jobAdsModule.model.JobAdsModel;
import au.com.carecareers.android.jobDetailsModule.JobDetailsActivity;
import au.com.carecareers.android.jobSearchModule.injection.SearchModule;
import au.com.carecareers.android.jobSearchModule.model.LocationModel;
import au.com.carecareers.android.loginModule.getPages.PagesActivity;
import au.com.carecareers.android.loginModule.register.adapter.SpinnerAdapter;
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

    ArrayList<LocationModel.LocationResponse.Embedded.SearchLocation> countryList;
    @Inject
    SharedPreferenceManager preferenceManager;
    ArrayList<String> countryName;
    private int pos;
    private int id;

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
    }

    private void setUpButton() {
        SpannableString spanString = new SpannableString(getResources().getString(R.string.tv_starting_out));
        spanString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textColorPrimary)), 0, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, 12, 0);
        btnStartingOut.setText(spanString);
    }

    private void setUpWelcome() {
        String fullName = preferenceManager.getStringValues(AppContract.Preferences.FIRST_NAME)
                + " " +
                preferenceManager.getStringValues(AppContract.Preferences.LAST_NAME);
        SpannableString nameString = new SpannableString("Welcome, " + fullName);
        int namelength = 9 + fullName.length();
        nameString.setSpan(new StyleSpan(Typeface.BOLD), 8, namelength, 0);
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
        countryName = new ArrayList<>();
        countryList = new ArrayList<>();
        countryName.add("Select a location");
        for (int i = 0; i < locationResponse.getEmbedded().getLocations().size(); i++) {
            LocationModel.LocationResponse.Embedded.SearchLocation searchLocations = new LocationModel.LocationResponse.Embedded.SearchLocation();
            searchLocations.name = locationResponse.getEmbedded().getLocations().get(i).getName();
            searchLocations.id = locationResponse.getEmbedded().getLocations().get(i).getId();
            countryName.add(searchLocations.name);
            countryList.add(searchLocations);
            AppLog.d("country:" + searchLocations.name);
            AppLog.d("country Id:" + searchLocations.id);
        }
        final SpinnerAdapter statesAdapter = new SpinnerAdapter(getActivity(), R.layout.item_spinner, countryName, AppContract.Extras.COUNTRYLIST);
        statesAdapter.setDropDownViewResource(R.layout.custom_dropdown);
        spinnerLocations.setAdapter(statesAdapter);
//        spinnerLocations.setSelection(statesAdapter.getCount());


        spinnerLocations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String countryNames = countryList.get(position).name;
                int countryId = countryList.get(position).id;
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
    public void navigateToJobAds(JobAdsModel.JobAdsResponse jobAdsResponse) {
        Gson gson = new Gson();
        JobAdsActivity.start(getActivity(), gson.toJson(jobAdsResponse));
        transitionActivityOpen();
    }

    @OnClick(R.id.btn_job_search)
    void onJobSearchClicked() {
        String keywords = etSearchKeyword.getText().toString().trim();
        /*int locationId = countryList.get(0).id;
        presenter.searchJobs(keywords, locationId);*/
        JobDetailsActivity.start(getActivity());
        transitionActivityOpen();

//        JobAdsActivity.start(getActivity(), "");
//        transitionActivityOpen();
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }
}
