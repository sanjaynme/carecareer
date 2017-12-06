package au.com.carecareers.android.jobDetailsModule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.jobAdsModule.JobAdsActivity;
import au.com.carecareers.android.jobAdsModule.model.JobAdsModel;
import au.com.carecareers.android.jobDetailsModule.injection.JobAdsDetailsModule;
import au.com.carecareers.android.jobDetailsModule.model.JobsDetailsModel;
import butterknife.BindView;

/**
 * Created by Nikesh on 12/1/2017.
 */

public class JobDetailsActivity extends BaseActivity implements JobDetailsContract.IJobDetailsView {

    private JobAdsModel.JobAdsResponse.Embedded.Job jobSearchResponse;

    @Inject
    JobDetailsPresenter presenter;
    private Integer jobId;

    @BindView(R.id.tv_job_details)
    TextView tvJobDetails;
    @BindView(R.id.iv_job_details)
    ImageView ivJobDetails;
    @BindView(R.id.iv_job_details_logo)
    ImageView ivJobDetailsLogo;
    @BindView(R.id.tv_details_location)
    TextView tvDetailsLocation;
    @BindView(R.id.tv_details_profession)
    TextView tvDetailsProfession;
    @BindView(R.id.tv_details_sector)
    TextView tvDetailsSector;
    @BindView(R.id.tv_details_posted_date)
    TextView tvDetailsPostedDate;
    @BindView(R.id.tv_details_description)
    TextView tvDetailsDescription;
    @BindView(R.id.btn_view_jobs_advertiser)
    Button btnViewJobsAdvertiser;
    @BindView(R.id.iv_job_details_save)
    ImageView ivJobSave;
    @BindView(R.id.iv_job_details_share)
    ImageView ivJobShare;
    @BindView(R.id.btn_job_apply)
    Button btnJobApply;

    public static void start(Context context, String extraData) {
        Intent intent = new Intent();
        intent.setClass(context, JobDetailsActivity.class);
        intent.putExtra(AppContract.Extras.DATA, extraData);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onAttach(this);
        if (getIntent().getExtras() != null) {
            jobSearchResponse = new Gson().fromJson(getIntent().getStringExtra(AppContract.Extras.DATA), JobAdsModel.JobAdsResponse.Embedded.Job.class);
        }
        jobId = Integer.valueOf(jobSearchResponse.id);

        presenter.getJobDetails(jobId);
    }

    @Override
    public void setupToolbar() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_job_details;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideJobDetailsSubComponent(new JobAdsDetailsModule()).inject(this);
    }

    @Override
    public void populateJobDetails(JobsDetailsModel.JobsDetailResponse jobsDetailResponse) {
        Picasso.with(ivJobDetails.getContext())
                .load(jobsDetailResponse.embedded.advertiser.links.logoUrl.href)
                .resize(200, 200)
                .centerCrop()
                .placeholder(R.color.colorGrey700)
                .into(ivJobDetails);

        Picasso.with(ivJobDetailsLogo.getContext())
                .load(jobsDetailResponse.embedded.advertiser.links.logoUrl.href)
                .resize(200, 200)
                .centerCrop()
                .placeholder(R.color.colorGrey700)
                .into(ivJobDetailsLogo);

        tvJobDetails.setText(convertToBold("Job Details ", ""));
        tvDetailsDescription.setText(convertToBold("Description \n \n \n \n", jobsDetailResponse.description));
        tvDetailsLocation.setText(convertToBold("Location: ", jobsDetailResponse.embedded.location.name));
        tvDetailsSector.setText(convertToBold("Sector: ", jobsDetailResponse.embedded.sector.name));
        tvDetailsPostedDate.setText(convertToBold("Date posted: ", convertDateFormat(jobsDetailResponse.postedDate)));
        tvDetailsProfession.setText(convertToBold("Profession: ", jobsDetailResponse.embedded.profession.name));

        btnJobApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnViewJobsAdvertiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.searchJobs(jobsDetailResponse.embedded.advertiser.id);
            }
        });

        ivJobSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ivJobShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void navigateToJobAds(JobAdsModel.JobAdsResponse jobAdsResponse) {
        Gson gson = new Gson();
        JobAdsActivity.start(this, "", gson.toJson(jobAdsResponse));
        transitionActivityOpen();
    }

    private String convertDateFormat(String dateString) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(convertedDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(convertedDate);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/YYYY");
        String formatedDate = dateFormatter.format(cal.getTime());
        System.out.println("formatedDate : " + formatedDate);
        return formatedDate;
    }

    private CharSequence convertToBold(String hint, String normalText) {
        SpannableString convertedBoldString = new SpannableString(hint);
        SpannableString normalString = new SpannableString(normalText);
        convertedBoldString.setSpan(new StyleSpan(Typeface.BOLD), 0, convertedBoldString.length(), 0);
        return TextUtils.concat(convertedBoldString, normalString);
    }

}
