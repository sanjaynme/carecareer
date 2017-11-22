package au.com.carecareers.android.loginModule.termsAndCondition.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.loginModule.termsAndCondition.TermsAndConditionActivity;
import dagger.Subcomponent;

/**
 * Created by Sanjay on 14/11/2017.
 */
@ActivityScope
@Subcomponent(modules = TermsAndConditionsModule.class)
public interface TermsAndConditionSubComponent {
    void inject(TermsAndConditionActivity termsAndConditionActivity);
}
