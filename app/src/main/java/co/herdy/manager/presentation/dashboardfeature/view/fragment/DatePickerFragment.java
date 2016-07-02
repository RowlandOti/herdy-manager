package co.herdy.manager.presentation.dashboardfeature.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.herdy.manager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends Fragment {

    @Bind(R.id.datePicker)
    DatePicker mDatePicker;


    public DatePickerFragment() {

    }

    public static DatePickerFragment newInstance(Bundle args) {
        DatePickerFragment fragmentInstance = new DatePickerFragment();
        if (args != null) {
            fragmentInstance.setArguments(args);
        }
        return fragmentInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_date_picker, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
