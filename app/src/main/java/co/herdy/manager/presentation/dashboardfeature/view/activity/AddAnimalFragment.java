package co.herdy.manager.presentation.dashboardfeature.view.activity;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.herdy.manager.R;
import co.herdy.manager.presentation.view.fragment.ABaseFragment;

/**
 * A simple {@link ABaseFragment} subclass.
 */
public class AddAnimalFragment extends ABaseFragment {

    @Bind(R.id.bt_add_animal)
    Button mAddButton;

    public AddAnimalFragment() {

    }

    public static AddAnimalFragment newInstance(Bundle args) {
        AddAnimalFragment fragmentInstance = new AddAnimalFragment();
        if (args != null) {
            fragmentInstance.setArguments(args);
        }
        return fragmentInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_animal, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.bt_add_animal)
    public void onClickAddAnimal() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

}
