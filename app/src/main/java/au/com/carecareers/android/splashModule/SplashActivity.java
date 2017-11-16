package au.com.carecareers.android.splashModule;

import android.os.Bundle;
import android.os.Handler;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.landing.LandingActivity;
import au.com.carecareers.android.splashModule.injection.SplashModule;
import au.com.carecareers.android.splashModule.model.AuthenticationModel;

public class SplashActivity extends BaseActivity implements SplashContract.ISplashView {
    @Inject
    SplashPresenter presenter;

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
        baseComponent.provideSplashSubComponent(new SplashModule()).inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onAttach(this);
        new Handler().
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.auth(new AuthenticationModel());
                    }
                }, 2000);
    }

    @Override
    public void navigateToLandingActivity() {
        LandingActivity.start(SplashActivity.this);
        finish();
        transitionFadeOut();
    }

       /* if (preferenceManager.getBoolValues(Contracts.SharedPrefKeys.IS_LOGGED_IN)) {
            if (preferenceManager.getIntValues(Contracts.SharedPrefKeys.IS_PROFILE_COMPLETE)
                    == Contracts.ProfileLoginStatus.PROFILECOMPLETE) {
//                HomeActivity.start(SplashActivity.this);
                finish();
                transitionFadeOut();
            } else {
//                UpdateActivity.start(SplashActivity.this);
                finish();
                transitionFadeOut();
            }
        } else {
            new Handler().
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LandingActivity.start(SplashActivity.this);
                            finish();
                            transitionFadeOut();
                        }
                    }, 2000);
        }
    }*/
}
