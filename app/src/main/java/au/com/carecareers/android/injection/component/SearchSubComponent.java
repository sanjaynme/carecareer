package au.com.carecareers.android.injection.component;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.jobSearchModule.SearchFragment;
import au.com.carecareers.android.jobSearchModule.injection.SearchModule;
import dagger.Subcomponent;

/**
 * Created by Nikesh on 11/29/2017.
 */
@ActivityScope
@Subcomponent(modules = SearchModule.class)
public interface SearchSubComponent {
    void inject(SearchFragment searchFragment);
}
