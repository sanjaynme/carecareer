package au.com.carecareers.android.jobModule.applyJobModule.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import dagger.Subcomponent;

/**
 * Created by Nikesh on 12/1/2017.
 */
@ActivityScope
@Subcomponent(modules = ApplyJobsModule.class)
public interface ApplyJobsModuleSubComponent {
}
