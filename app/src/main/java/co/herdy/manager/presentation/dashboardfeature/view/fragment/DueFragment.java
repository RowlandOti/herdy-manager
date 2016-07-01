package co.herdy.manager.presentation.dashboardfeature.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.herdy.manager.R;
import co.herdy.manager.presentation.view.fragment.ABaseFragment;

/**
 * A simple {@link ABaseFragment} subclass.
 */
public class DueFragment extends ABaseFragment {


    public DueFragment() {
        // Required empty public constructor
    }

    public static DueFragment newInstance(Bundle args) {
        DueFragment fragmentInstance = new DueFragment();
        if (args != null) {
            fragmentInstance.setArguments(args);
        }
        return fragmentInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_due, container, false);
    }

}
