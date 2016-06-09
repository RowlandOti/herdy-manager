package co.herdy.manager.presentation.splashfeature.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import butterknife.ButterKnife;
import co.herdy.manager.R;
import co.herdy.manager.data.splashfeature.preference.SplashPreferenceManager;
import co.herdy.manager.presentation.authfeature.view.activity.AuthActivity;
import co.herdy.manager.presentation.onboarderfeature.view.activity.OnBoarderActivity;
import co.herdy.manager.presentation.splashfeature.view.fragment.SplashFragment;
import co.herdy.manager.presentation.view.activity.ABaseActivity;

public class SplashActivity extends ABaseActivity {

    // Class log identifier
    public final static String LOG_TAG = SplashActivity.class.getSimpleName();

    private final int SPLASH_DISPLAY_DURATION = 3000;
    private SplashPreferenceManager prefManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setStatusbarTransparent(true);

        // Checking for first time launch - before calling setContentView()
        prefManager = new SplashPreferenceManager(this);
        if (!prefManager.getIsFirstTimeLaunch()) {
            loadAuthActivity();
            finish();
        }
        // Check that the activity is using the layout with the container id
        if (findViewById(R.id.splash_fragment_container) != null) {
            // If we're being restored from a previous state, then we don't need to do
            // anything and should return or else we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            } else {
                showSplashFragment(null);
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadIntroActivity();
                }
            }, SPLASH_DISPLAY_DURATION);
        }
    }

    private void showSplashFragment(Bundle args) {
        SplashFragment fragment = SplashFragment.newInstance(args);
        replaceFragment(R.id.splash_fragment_container, fragment, false, true);
    }

    public void loadIntroActivity() {
        Intent intent = new Intent(this, OnBoarderActivity.class);
        startActivity(intent);
        animateActivityTransition();
        finish();
    }

    private void loadAuthActivity() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        animateActivityTransition();
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
