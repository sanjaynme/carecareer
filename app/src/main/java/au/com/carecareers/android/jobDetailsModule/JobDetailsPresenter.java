package au.com.carecareers.android.jobDetailsModule;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.presenter.BasePresenter;
import au.com.carecareers.android.jobAdsModule.model.JobAdsModel;
import au.com.carecareers.android.jobDetailsModule.model.JobsDetailsModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nikesh on 12/1/2017.
 */

public class JobDetailsPresenter extends BasePresenter<JobDetailsContract.IJobDetailsView, JobDetailsContract.IJobDetailsInteractor> implements JobDetailsContract.IJobDetailsPresenter {

    @Inject
    public JobDetailsPresenter(JobDetailsContract.IJobDetailsInteractor interactor, CompositeDisposable compositeDisposable) {
        super(interactor, compositeDisposable);
    }

    @Override
    public void getJobDetails(int jobSearchId) {
        getView().showProgressDialog(R.string.msg_loading);
        getCompositeDisposable().add(getInteractor().getJobDetails(jobSearchId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getJobDetailsObserver()));
    }

    @Override
    public void searchJobs(Integer id) {
        getView().showProgressDialog(R.string.msg_loading);
        getCompositeDisposable().add(getInteractor().searchJobAds(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getJobSearchObserver()));
    }

    private DisposableObserver<JobAdsModel.JobAdsResponse> getJobSearchObserver() {
        return new DisposableObserver<JobAdsModel.JobAdsResponse>() {
            @Override
            public void onNext(JobAdsModel.JobAdsResponse jobAdsResponse) {
                if (getView() != null) {
                    if (jobAdsResponse != null){
                        getView().hideProgressDialog();
                        getView().navigateToJobAds(jobAdsResponse);
                    }

                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }


    private DisposableObserver<JobsDetailsModel.JobsDetailResponse> getJobDetailsObserver() {
        return new DisposableObserver<JobsDetailsModel.JobsDetailResponse>() {
            @Override
            public void onNext(JobsDetailsModel.JobsDetailResponse jobsDetailResponse) {
                if(isViewAttached()){
                    if(jobsDetailResponse!=null)
                    getView().hideProgressDialog();
                    getView().populateJobDetails(jobsDetailResponse);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

}
