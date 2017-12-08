package au.com.carecareers.android.jobModule.jobDetailsModule.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.jobModule.jobDetailsModule.JobDetailsActivity;
import dagger.Subcomponent;

/**
 * Created by Nikesh on 12/1/2017.
 */
@ActivityScope
@Subcomponent(modules = JobAdsDetailsModule.class)
public interface JobAdsDetailsSubComponent {
    void inject(JobDetailsActivity jobDetailsActivity);
}
