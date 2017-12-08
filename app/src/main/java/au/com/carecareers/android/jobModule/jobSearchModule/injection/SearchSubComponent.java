package au.com.carecareers.android.jobModule.jobSearchModule.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.jobModule.jobSearchModule.SearchFragment;
import dagger.Subcomponent;

/**
 * Created by Nikesh on 11/29/2017.
 */
@ActivityScope
@Subcomponent(modules = SearchModule.class)
public interface SearchSubComponent {
    void inject(SearchFragment searchFragment);
}
