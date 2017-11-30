package au.com.carecareers.android.splashModule;

import android.os.Bundle;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.contracts.AppContract;
import au.com.carecareers.android.data.local.SharedPreferenceManager;
import au.com.carecareers.android.homeModule.HomeActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.loginModule.landing.LandingActivity;
import au.com.carecareers.android.profileModule.profileSetup.ProfileSetupActivity;
import au.com.carecareers.android.splashModule.injection.SplashModule;

public class SplashActivity extends BaseActivity implements SplashContract.ISplashView {
    @Inject
    SplashPresenter presenter;
    @Inject
    SharedPreferenceManager preferenceManager;

    @Override
    protected int getLayout() {
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
        presenter.auth();
    }

    @Override
    public void navigateToLandingActivity() {
        if (preferenceManager.getBoolValues(AppContract.Preferences.IS_LOGGED_IN)) {
            if (preferenceManager.getBoolValues(AppContract.Preferences.IS_PROFILE_COMPLETE)) {
                HomeActivity.start(SplashActivity.this);
                finish();
                transitionFadeOut();
            } else {
                ProfileSetupActivity.start(this);
                finish();
                transitionFadeOut();
            }
        } else {
            LandingActivity.start(this);
            finish();
            transitionFadeOut();
        }
    }

    @Override
    public void setupToolbar() {

    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
