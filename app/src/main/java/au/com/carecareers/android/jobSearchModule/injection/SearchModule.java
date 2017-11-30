package au.com.carecareers.android.jobSearchModule.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.jobSearchModule.SearchContract;
import au.com.carecareers.android.jobSearchModule.SearchInteractor;
import au.com.carecareers.android.jobSearchModule.SearchPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Nikesh on 11/29/2017.
 */
@Module
public class SearchModule {
    public SearchModule() {
    }

    @Provides
    @ActivityScope
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ActivityScope
    SearchContract.ISearchInteractor provideSearchInteractor(SearchInteractor searchInteractor) {
        return searchInteractor;
    }

    @Provides
    @ActivityScope
    SearchContract.ISearchPresenter provideSearchPresenter(SearchPresenter searchPresenter) {
        return searchPresenter;
    }

}
