package au.com.carecareers.android.loginModule.getPages.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.loginModule.getPages.PagesContract;
import au.com.carecareers.android.loginModule.getPages.PagesInteractor;
import au.com.carecareers.android.loginModule.getPages.PagesPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Sanjay on 14/11/2017.
 */
@Module
public class PagesModule {
    public PagesModule() {

    }

    @Provides
    @ActivityScope
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ActivityScope
    PagesContract.IPagesInteractor provideTermsAndConditionsInteractor(PagesInteractor termsAndConditionInteractor) {
        return termsAndConditionInteractor;
    }

    @Provides
    @ActivityScope
    PagesContract.IPagesPresenter provideTermsAndConditionsPresenter(PagesPresenter pagesPresenter) {
        return pagesPresenter;
    }
}
