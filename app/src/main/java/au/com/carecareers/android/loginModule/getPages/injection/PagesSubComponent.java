package au.com.carecareers.android.loginModule.getPages.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.loginModule.getPages.PagesActivity;
import dagger.Subcomponent;

/**
 * Created by Sanjay on 14/11/2017.
 */
@ActivityScope
@Subcomponent(modules = PagesModule.class)
public interface PagesSubComponent {
    void inject(PagesActivity termsAndConditionActivity);
}
