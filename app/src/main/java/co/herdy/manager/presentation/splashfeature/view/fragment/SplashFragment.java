package co.herdy.manager.presentation.splashfeature.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.herdy.manager.R;
import co.herdy.manager.presentation.view.fragment.ABaseFragment;

/**
 *
 */
public class SplashFragment extends ABaseFragment {

    // Class log identifier
    public final static String LOG_TAG = SplashFragment.class.getSimpleName();

    private static final SpringConfig SPRING_CONFIG = SpringConfig.fromOrigamiTensionAndFriction(40, 1.5f);
    private Spring mSpring;

    @Bind(R.id.splash_layout)
    LinearLayout mSplashLayout;

    public SplashFragment() {

    }

    // Actual method to use to create new fragment instance externally
    public static SplashFragment newInstance(@Nullable Bundle args) {
        SplashFragment fragmentInstance = new SplashFragment();
        if (args != null) {
            fragmentInstance.setArguments(args);
        }
        return fragmentInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View fragmentView = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        animateSplash();
    }

    private void animateSplash() {
        // Create a system to run the physics loop for a set of springs.
        SpringSystem springSystem = SpringSystem.create();
        // Add a mSpring to the system.
        mSpring = springSystem.createSpring();
        mSpring.setSpringConfig(SPRING_CONFIG);
        // Add a listener to observe the motion of the mSpring.
        mSpring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                // You can observe the updates in the mSpring
                // state by asking its current value in onSpringUpdate.
                float value = (float) spring.getCurrentValue();
                float scale = 3f - (value * 2f);
                mSplashLayout.setScaleX(scale);
                mSplashLayout.setScaleY(scale);
            }
        });
        // Set the mSpring in motion; moving from 0 to 0.5
        mSpring.setEndValue(1);
    }

    @Override
    public void onResume() {
        super.onResume();
        animateSplash();
    }

    @Override
    public void onPause() {
        super.onPause();
        mSpring.destroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        mSpring.destroy();
    }
}
