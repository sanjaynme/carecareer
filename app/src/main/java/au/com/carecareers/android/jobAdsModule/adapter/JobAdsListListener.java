package au.com.carecareers.android.jobAdsModule.adapter;

import au.com.carecareers.android.jobAdsModule.model.JobAdsModel;

/**
 * Created by Nikesh on 12/1/2017.
 */

public interface JobAdsListListener {
    void onRecyclerViewDetailsItemClick(JobAdsModel.JobAdsResponse.Embedded.Job jobDetails);
    void onRecyclerViewShareItemClick(String jobAdId);
    void onRecyclerViewSaveItemClick(String jobAdId);
}
