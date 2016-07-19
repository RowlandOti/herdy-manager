package co.herdy.manager.presentation.dashboardfeature.view.fragment;


import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidFragment;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.herdy.manager.R;
import co.herdy.manager.presentation.view.fragment.ABaseFragment;

/**
 * A simple {@link ABaseFragment} subclass.
 */
public class AddAnimalFragment extends ABaseFragment {

    // Class log identifier
    private final static String LOG_TAG = AddAnimalFragment.class.getSimpleName();

    @Bind(R.id.bt_add_animal)
    Button mAddButton;
    @Bind(R.id.tv_dob_title)
    TextView mInputEditText;
    @Bind(R.id.add_animal_form_normal_mode_container)
    NestedScrollView mNormalModelayoutContainer;
    @Bind(R.id.add_animal_form_edit_mode_container)
    FrameLayout mEditModelayoutContainer;
    private int heightOfScreen;

    public AddAnimalFragment() {
        setRetainInstance(true);

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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        configureLayoutListener();
    }

    @OnClick(R.id.tv_dob_title)
    public void onClickEditDoB() {
        FragmentManager fm = getChildFragmentManager();
        CaldroidFragment fragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark);
        fragment.setArguments(args);

        mEditModelayoutContainer.setVisibility(View.VISIBLE);
        fm.beginTransaction().replace(R.id.add_animal_form_edit_mode_container, fragment).commit();
        animate();
        mNormalModelayoutContainer.setVisibility(View.GONE);
        Log.d(LOG_TAG, "Slide in Children "+mEditModelayoutContainer.getChildCount());
    }

    @OnClick(R.id.bt_add_animal)
    public void onClickAddAnimal() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    private void configureLayoutListener() {
        mNormalModelayoutContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                heightOfScreen = mNormalModelayoutContainer.getHeight();
                ViewTreeObserver viewTreeObserver = mNormalModelayoutContainer.getViewTreeObserver();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                } else {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    private void animate() {
        View viewToShiftOut = mNormalModelayoutContainer;
        View viewToShiftIn = mEditModelayoutContainer;
        ObjectAnimator outAnim = ObjectAnimator.ofFloat(viewToShiftOut, "y", 0, -heightOfScreen);
        ObjectAnimator inAnim = ObjectAnimator.ofFloat(viewToShiftIn, "y", heightOfScreen , 0);
        outAnim.setDuration(1000);
        inAnim.setDuration(1000);
        outAnim.start();
        inAnim.start();
    }
}
