package co.herdy.manager.presentation.dashboardfeature.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.herdy.manager.R;
import co.herdy.manager.presentation.dashboardfeature.view.activity.DashBoardActivity;
import co.herdy.manager.presentation.dashboardfeature.view.adapter.SmartNestedViewPagerAdapter;
import co.herdy.manager.presentation.view.fragment.ABaseFragment;

/**
 * A simple {@link ABaseFragment} subclass.
 */
public class CalendarFragment extends ABaseFragment {

    // The class Log identifier
    private static final String LOG_TAG = CalendarFragment.class.getSimpleName();
    public static final String SELECTED_TAB_KEY = "SELECTED_TAB";

    TabLayout mSlidingTabStrips;
    @Bind(R.id.calendar_view_pager)
    ViewPager mViewPager;

    private String[] TITLES = {"Servings Due", "Kindlings Due", "Nestings Due", "Weanings Due", "Sexings Due"};
    private SmartNestedViewPagerAdapter pagerAdapter;
    private int selectedTabStrip = 0;


    public CalendarFragment() {

    }

    public static CalendarFragment newInstance(Bundle args) {
        CalendarFragment fragmentInstance = new CalendarFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        pagerAdapter = new SmartNestedViewPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);

        ViewGroup parentAppBarLayout = ((DashBoardActivity) getActivity()).getAppBarLayout();
        View rootTab = getActivity().getLayoutInflater().inflate(R.layout.inc_tab_calendar, parentAppBarLayout);
        mSlidingTabStrips = (TabLayout) rootTab.findViewById(R.id.slidingTabStrips);
        mSlidingTabStrips.setupWithViewPager(mViewPager);
        if (savedInstanceState != null) {
            selectedTabStrip = savedInstanceState.getInt(SELECTED_TAB_KEY, selectedTabStrip);
            mViewPager.setCurrentItem(selectedTabStrip, true);
        }
    }

    // Save data for this fragment
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        selectedTabStrip = mViewPager.getCurrentItem();
        outState.putInt(SELECTED_TAB_KEY, selectedTabStrip);
    }

    @Override
    public void onStop() {
        ViewGroup parentAppBarLayout = ((DashBoardActivity) getActivity()).getAppBarLayout();
        parentAppBarLayout.removeView(mSlidingTabStrips);
    }

    public String[] getTITLES() {
        return TITLES;
    }
}
