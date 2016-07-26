package co.herdy.manager.presentation.dashboardfeature.view.fragment;


import android.os.Bundle;
import android.support.design.widget.SlidingTabStripLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.herdy.manager.R;
import co.herdy.manager.presentation.dashboardfeature.view.adapter.CalendarPagerAdapter;
import co.herdy.manager.presentation.view.fragment.ABaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class BreedingFragment extends ABaseFragment {

    // The class Log identifier
    private static final String LOG_TAG = BreedingFragment.class.getSimpleName();
    public static final String SELECTED_TAB_KEY = "SELECTED_TAB";

    SlidingTabStripLayout mSlidingTabStrips;
    @Bind(R.id.breeding_view_pager)
    ViewPager mViewPager;

    private String[] TITLES = {"Ready for Mating ", "Resting", "Pregnant", "Lactating"};
    private CalendarPagerAdapter pagerAdapter;
    private int selectedTabStrip = 0;

    public BreedingFragment() {
        // Required empty public constructor
    }


    public static BreedingFragment newInstance(Bundle args) {
        BreedingFragment fragmentInstance = new BreedingFragment();
        if (args != null) {
            fragmentInstance.setArguments(args);
        }
        return fragmentInstance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_breeding, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Change Title
        getActivity().setTitle("Breeding");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

