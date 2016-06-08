package co.herdy.manager.presentation.onboarderfeature.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import co.herdy.manager.presentation.onboarderfeature.view.fragment.OnBoarder;
import co.herdy.manager.presentation.onboarderfeature.view.fragment.OnBoarderFragment;
import co.herdy.manager.presentation.view.adapter.SmartFragmentStatePagerAdapter;

public class OnBoarderAdapter extends SmartFragmentStatePagerAdapter {

    List<OnBoarder> mFragmentsPages = new ArrayList<OnBoarder>();

    public OnBoarderAdapter(List<OnBoarder> fragmentPage, FragmentManager fm) {
        super(fm);
        this.mFragmentsPages = fragmentPage;
    }

    @Override
    public Fragment getItem(int position) {
        return OnBoarderFragment.newInstance(mFragmentsPages.get(position));
    }

    @Override
    public int getCount() {
        return mFragmentsPages.size();
    }
}
