package au.com.carecareers.android.splashModule;

import android.os.Bundle;
import android.os.Handler;

import au.com.carecareers.android.R;
import au.com.carecareers.android.base.BaseActivity;
import au.com.carecareers.android.injection.component.BaseComponent;
import au.com.carecareers.android.profileModule.profileSetup.ProfileSetupActivity;

public class SplashActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void injectComponent(BaseComponent baseComponent) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ProfileSetupActivity.start(SplashActivity.this);
                        finish();
                        transitionFadeOut();
                    }
                }, 2000);
    }

    @Override
    public void setupToolbar() {

    }
       /* if (preferenceManager.getBoolValues(Contracts.SharedPrefKeys.IS_LOGGED_IN)) {
            if (preferenceManager.getIntValues(Contracts.SharedPrefKeys.IS_PROFILE_COMPLETE)
                    == Contracts.ProfileLoginStatus.PROFILECOMPLETE) {
//                HomeActivity.startForResult(SplashActivity.this);
                finish();
                transitionFadeOut();
            } else {
//                UpdateActivity.startForResult(SplashActivity.this);
                finish();
                transitionFadeOut();
            }
        } else {
            new Handler().
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LandingActivity.startForResult(SplashActivity.this);
                            finish();
                            transitionFadeOut();
                        }
                    }, 2000);
        }
    }*/
}
