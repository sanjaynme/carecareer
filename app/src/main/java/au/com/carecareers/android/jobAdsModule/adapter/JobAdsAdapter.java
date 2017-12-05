package au.com.carecareers.android.jobAdsModule.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import au.com.carecareers.android.R;
import au.com.carecareers.android.jobAdsModule.model.JobAdsModel;

/**
 * Created by Sanjay on 12/1/2017.
 */

public class JobAdsAdapter extends RecyclerView.Adapter<JobAdsAdapter.JobAdsViewHolder> {

    private final List<JobAdsModel.JobAdsResponse.Embedded.Job> jobSearchResponseData;
    private final Context ctx;
    private final JobAdsListListener mListener;

    public JobAdsAdapter(Context context, List<JobAdsModel.JobAdsResponse.Embedded.Job> jobSearchResponse, JobAdsListListener jobAdsListListener) {
        this.jobSearchResponseData = jobSearchResponse;
        this.ctx = context;
        this.mListener = jobAdsListListener;
    }

    @Override
    public JobAdsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_job_ads, parent, false);
        return new JobAdsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JobAdsViewHolder holder, int position) {
        if (jobSearchResponseData != null) {
            String location = jobSearchResponseData.get(position).locationName;
            String postedDate = convertDateFormat(jobSearchResponseData.get(position).postedDate);
            String worktypeName = jobSearchResponseData.get(position).worktypeName;
            String professionName = jobSearchResponseData.get(position).professionName;
            holder.tvItemCompanyName.setText(convertToBold(jobSearchResponseData.get(position).locationName, ""));
            holder.tvItemLocation.setText(convertToBold("Location: ", location));
            holder.tvItemPostedDate.setText(convertToBold("Date posted: ", postedDate));
            holder.tvItemWorkType.setText(convertToBold("Worktype: ", worktypeName));
            holder.tvItemProfession.setText(convertToBold("Profession: ", professionName));

            Picasso.with(holder.ivItemBackground.getContext())
                    .load(jobSearchResponseData.get(position).links.advertiserLogoUrl.href)
                    .resize(200, 200)
                    .centerCrop()
                    .error(R.color.colorGrey700)
                    .into(holder.ivItemBackground, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            holder.progressBar.setVisibility(View.VISIBLE);

                        }
                    });

            holder.ivItemSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onRecyclerViewSaveItemClick(jobSearchResponseData.get(holder.getAdapterPosition()).id.toString());
                }
            });
            holder.ivItemShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onRecyclerViewShareItemClick(jobSearchResponseData.get(holder.getAdapterPosition()).id.toString());
                }
            });
            holder.btnJobViewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onRecyclerViewDetailsItemClick(jobSearchResponseData.get(holder.getAdapterPosition()));
                }
            });
        }
    }

    private String convertDateFormat(String dateString) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
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

    @Override
    public int getItemCount() {
        return jobSearchResponseData.size();
    }

    class JobAdsViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemCompanyName, tvItemProfession, tvItemPostedDate, tvItemWorkType, tvItemLocation;
        Button btnJobViewDetails;
        ImageView ivItemBackground, ivItemSave, ivItemShare;
        ProgressBar progressBar;

        JobAdsViewHolder(View itemView) {
            super(itemView);
            tvItemCompanyName = itemView.findViewById(R.id.tv_item_company_name);
            tvItemProfession = itemView.findViewById(R.id.tv_item_profession);
            tvItemPostedDate = itemView.findViewById(R.id.tv_item_posted_date);
            tvItemWorkType = itemView.findViewById(R.id.tv_item_work_type);
            tvItemLocation = itemView.findViewById(R.id.tv_item_location);
            btnJobViewDetails = itemView.findViewById(R.id.btn_job_view_details);
            ivItemBackground = itemView.findViewById(R.id.iv_item_background);
            ivItemSave = itemView.findViewById(R.id.iv_item_save);
            ivItemShare = itemView.findViewById(R.id.iv_item_share);
            progressBar = itemView.findViewById(R.id.jobads_progress_bar);
        }
    }
}
