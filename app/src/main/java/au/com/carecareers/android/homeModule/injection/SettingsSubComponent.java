package au.com.carecareers.android.homeModule.injection;

import au.com.carecareers.android.homeModule.SettingsFragment;
import au.com.carecareers.android.injection.scope.ActivityScope;
import dagger.Subcomponent;

/**
 * Created by Nischal Manandhar on 14/11/2017.
 */
@ActivityScope
@Subcomponent(modules = SettingsModule.class)
public interface SettingsSubComponent {
    void inject(SettingsFragment settingsFragment);
}
