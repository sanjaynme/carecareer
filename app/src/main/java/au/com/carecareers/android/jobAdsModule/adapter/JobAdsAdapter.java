package au.com.carecareers.android.jobAdsModule.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import au.com.carecareers.android.R;
import au.com.carecareers.android.jobAdsModule.model.JobAdsModel;
import butterknife.BindView;

/**
 * Created by Sanjay on 12/1/2017.
 */

public class JobAdsAdapter extends RecyclerView.Adapter<JobAdsAdapter.JobAdsViewHolder> {

    private final List<JobAdsModel.JobAdsResponse> jobSearchResponseData;
    private final Context ctx;
    private final JobAdsListListener mListener;

    public JobAdsAdapter(Context context, List<JobAdsModel.JobAdsResponse> jobSearchResponse, JobAdsListListener jobAdsListListener) {
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
        if (jobSearchResponseData.get(position).jobTypes._1.equalsIgnoreCase("_1")) {
            mListener.onRecyclerLongItemClick(jobSearchResponseData.get(holder.getAdapterPosition()).pageCount, jobSearchResponseData.get(holder.getAdapterPosition()).pageCount);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class JobAdsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_company_name)
        TextView tvItemCompanyName;
        @BindView(R.id.tv_item_profession)
        TextView tvItemProfession;
        @BindView(R.id.tv_item_posted_date)
        TextView tvItemPostedDate;
        @BindView(R.id.tv_item_work_type)
        TextView tvItemWorkType;
        @BindView(R.id.tv_item_location)
        TextView tvItemLocation;
        @BindView(R.id.btn_job_view_details)
        Button btnJobViewDetails;
        @BindView(R.id.iv_item_background)
        ImageView ivItemBackground;
        @BindView(R.id.iv_item_save)
        ImageView ivItemSave;
        @BindView(R.id.iv_item_share)
        ImageView ivItemShare;

        public JobAdsViewHolder(View itemView) {
            super(itemView);

        }
    }
}
