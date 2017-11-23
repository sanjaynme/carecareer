package au.com.carecareers.android.loginModule.termsAndCondition.injection;

import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.loginModule.termsAndCondition.TermsAndConditionInteractor;
import au.com.carecareers.android.loginModule.termsAndCondition.TermsAndConditionPresenter;
import au.com.carecareers.android.loginModule.termsAndCondition.TermsAndConditionsContract;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Sanjay on 14/11/2017.
 */
@Module
public class TermsAndConditionsModule {
    public TermsAndConditionsModule() {

    }

    @Provides
    @ActivityScope
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ActivityScope
    TermsAndConditionsContract.ITermsAndConditionsInteractor provideTermsAndConditionsInteractor(TermsAndConditionInteractor termsAndConditionInteractor) {
        return termsAndConditionInteractor;
    }

    @Provides
    @ActivityScope
    TermsAndConditionsContract.ITermsAndConditionsPresenter provideTermsAndConditionsPresenter(TermsAndConditionPresenter termsAndConditionPresenter) {
        return termsAndConditionPresenter;
    }
}