package au.com.carecareers.android.jobSearchModule;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseFragment;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.jobSearchModule.model.LocationModel;
import au.com.carecareers.android.loginModule.register.adapter.SpinnerAdapter;
import au.com.carecareers.android.utilities.AppLog;
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

    @BindView(R.id.btn_search_out)
    Button btnSearchOut;
    @BindView(R.id.et_search_keyword)
    EditText etSearchKeyword;

    @BindView(R.id.spinner_locations)
    Spinner spinnerLocations;


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
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setUpWelcome();
        setUpButton();
        presenter.onAttach(this);
        presenter.loadLocations();
    }

    private void setUpButton() {
        SpannableString spanString = new SpannableString("Start tour \n Welcome sanjay");
        spanString.setSpan(new ForegroundColorSpan(Color.BLUE), 12, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 12, spanString.length(), 0);
        btnSearchOut.setText(spanString);
    }

    private void setUpWelcome() {

        Spannable wordtoSpan = new SpannableString("I know just how to whisper, And I know just how to cry,I know just where to find the answers");

        wordtoSpan.setSpan(new ForegroundColorSpan(Color.BLUE), 15, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvWelcomeUser.setText(wordtoSpan);
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
                String countryId = locationResponse.getEmbedded().getLocations().get(position).getId().toString();
                AppLog.d("position:" + position);
                AppLog.d("country selected name::::" + countryNames);
                AppLog.d("country selected id:::::" + countryId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @OnClick(R.id.btn_job_search)
    void onJobSearchClicked() {
        String keywords = etSearchKeyword.getText().toString().trim();
//        String locationId=
//        presenter.searchJobs();
    }

}
