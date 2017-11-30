package au.com.carecareers.android.settingsModule.injection;

import au.com.carecareers.android.settingsModule.SettingsActivity;
import au.com.carecareers.android.injection.scope.ActivityScope;
import dagger.Subcomponent;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */
@ActivityScope
@Subcomponent(modules = SettingsModule.class)
public interface SettingsSubComponent {
    void inject(SettingsActivity settingsActivity);
}
