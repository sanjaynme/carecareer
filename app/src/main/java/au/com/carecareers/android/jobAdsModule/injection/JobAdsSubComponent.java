package au.com.carecareers.android.jobAdsModule.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.jobAdsModule.JobAdsActivity;
import dagger.Subcomponent;

/**
 * Created by Nikesh on 12/1/2017.
 */
@ActivityScope
@Subcomponent(modules = JobAdsModule.class)
public interface JobAdsSubComponent {
    void inject(JobAdsActivity jobAdsActivity);
}
