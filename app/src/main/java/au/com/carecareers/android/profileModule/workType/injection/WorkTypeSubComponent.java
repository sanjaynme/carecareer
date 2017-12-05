package au.com.carecareers.android.profileModule.workType.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import dagger.Subcomponent;

/**
 * Created by Nischal Manandhar on 05/12/2017.
 */
@ActivityScope
@Subcomponent(modules = WorkTypeModule.class)
public interface WorkTypeSubComponent {
}
