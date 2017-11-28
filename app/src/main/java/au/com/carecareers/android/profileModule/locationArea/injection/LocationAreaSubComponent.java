package au.com.carecareers.android.profileModule.locationArea.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.locationArea.LocationAreaActivity;
import dagger.Subcomponent;

/**
 * Created by Nischal Manandhar on 28/11/2017.
 */
@ActivityScope
@Subcomponent(modules = LocationAreaModule.class)
public interface LocationAreaSubComponent {
    void inject(LocationAreaActivity locationAreaActivity);
}
