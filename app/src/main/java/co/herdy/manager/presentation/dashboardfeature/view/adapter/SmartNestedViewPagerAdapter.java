/*
 * Copyright 2015 Oti Rowland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package co.herdy.manager.presentation.dashboardfeature.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import co.herdy.manager.presentation.dashboardfeature.view.fragment.CalendarFragment;
import co.herdy.manager.presentation.dashboardfeature.view.fragment.DueFragment;
import co.herdy.manager.presentation.view.adapter.SmartFragmentStatePagerAdapter;


/**
 * Created by Rowland on 6/11/2015.
 */
public class SmartNestedViewPagerAdapter extends SmartFragmentStatePagerAdapter {

    private CalendarFragment ht = new CalendarFragment();

    public SmartNestedViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0: {
                // Servings fragment
                DueFragment popularFragment = new DueFragment().newInstance(null);
                return popularFragment;
            }
            case 1: {
                // Kindlings Rated fragment
                DueFragment popularFragment = new DueFragment().newInstance(null);
                return popularFragment;
            }
            case 2: {
                // Nestings fragment
                DueFragment popularFragment = new DueFragment().newInstance(null);
                return popularFragment;
            }
            case 3: {
                // Weanings fragment
                DueFragment popularFragment = new DueFragment().newInstance(null);
                return popularFragment;
            }
            case 4: {
                // Sexings fragment
                DueFragment popularFragment = new DueFragment().newInstance(null);
                return popularFragment;
            }

        }

        return null;
    }


    @Override
    public int getCount() {

        // get item count - equal to number of tabs

        return ht.getTITLES().length;

    }


    @Override
    public CharSequence getPageTitle(int position) {

        return ht.getTITLES()[position];
    }
}
