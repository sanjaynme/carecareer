package au.com.carecareers.android.jobSearchModule;

import au.com.carecareers.android.injection.scope.ActivityScope;
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
