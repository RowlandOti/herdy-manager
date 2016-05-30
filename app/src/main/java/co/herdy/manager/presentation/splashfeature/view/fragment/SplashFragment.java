package co.herdy.manager.presentation.splashfeature.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.herdy.manager.R;
import co.herdy.manager.presentation.view.fragment.ABaseFragment;

/**
 *
 */
public class SplashFragment extends ABaseFragment {


    public SplashFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

}
