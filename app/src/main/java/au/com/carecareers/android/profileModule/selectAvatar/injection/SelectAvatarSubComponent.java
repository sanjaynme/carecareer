package au.com.carecareers.android.profileModule.selectAvatar.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.selectAvatar.SelectAvatarActivity;
import dagger.Subcomponent;

/**
 * Created by Nischal Manandhar on 23/11/2017.
 */
@ActivityScope
@Subcomponent(modules = SelectAvatarModule.class)
public interface SelectAvatarSubComponent {
    void inject(SelectAvatarActivity selectAvatarActivity);
}
