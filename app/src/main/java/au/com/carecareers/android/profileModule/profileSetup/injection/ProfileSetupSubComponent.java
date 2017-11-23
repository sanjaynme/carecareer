package au.com.carecareers.android.profileModule.profileSetup.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.profileSetup.ProfileSetupActivity;
import dagger.Subcomponent;

/**
 * Created by Nischal Manandhar on 22/11/2017.
 */
@ActivityScope
@Subcomponent(modules = ProfileSetupModule.class)
public interface ProfileSetupSubComponent {
    void inject(ProfileSetupActivity profileSetupActivity);
}
