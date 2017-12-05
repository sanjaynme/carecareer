package au.com.carecareers.android.profileModule.professionRole.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.professionRole.ProfessionRoleActivity;
import dagger.Subcomponent;

/**
 * Created by Nischal Manandhar on 01/12/2017.
 */
@ActivityScope
@Subcomponent(modules = ProfessionRoleModule.class)
public interface ProfessionRoleSubcomponent {
    void inject(ProfessionRoleActivity professionRoleActivity);
}
