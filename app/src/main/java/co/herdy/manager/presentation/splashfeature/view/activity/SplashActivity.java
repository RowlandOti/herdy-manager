package co.herdy.manager.presentation.splashfeature.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.herdy.manager.R;
import co.herdy.manager.presentation.splashfeature.view.fragment.SplashFragment;
import co.herdy.manager.presentation.view.activity.ABaseActivity;

public class SplashActivity extends ABaseActivity {

    // Class log identifier
    public final static String LOG_TAG = SplashActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setStatusbarTransparent(true);
        // Check that the activity is using the layout with the container id
        if (findViewById(R.id.splash_fragment_container) != null) {
            // If we're being restored from a previous state, then we don't need to do
            // anything and should return or else we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            } else {
                showSplashFragment(null);
            }
        }


    }

    private void showSplashFragment(Bundle args) {
       SplashFragment fragment = SplashFragment.newInstance(args);
        replaceFragment(R.id.splash_fragment_container, fragment, false, true);
    }

    private void showIntroFragment() {
        
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
