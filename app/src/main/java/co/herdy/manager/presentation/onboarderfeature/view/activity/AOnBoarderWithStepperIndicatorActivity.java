package co.herdy.manager.presentation.onboarderfeature.view.activity;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.badoualy.stepperindicator.StepperIndicator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.herdy.manager.R;
import co.herdy.manager.presentation.onboarderfeature.view.ColorsArrayBuilder;
import co.herdy.manager.presentation.onboarderfeature.view.adapter.OnBoarderAdapter;
import co.herdy.manager.presentation.onboarderfeature.view.fragment.OnBoarder;
import co.herdy.manager.presentation.view.activity.ABaseActivity;

public abstract class AOnBoarderWithStepperIndicatorActivity extends ABaseActivity implements View.OnClickListener{

    private Integer[] mColors;
    private ArgbEvaluator evaluator;
    private OnBoarderAdapter onboarderAdapter;
    private boolean mShouldDarkenButtonsLayout = false;
    private boolean mShouldUseFloatingActionButton = false;

    @Bind(R.id.view_pager_onboarder)
    ViewPager mVpOnboarderPager;
    @Bind(R.id.stepper_indicator_view)
    StepperIndicator mStepperIndicatorView;
    @Bind(R.id.btn_next)
    ImageButton mIbNext;
    @Bind(R.id.btn_skip)
    Button mBtnSkip;
    @Bind(R.id.btn_finish)
    Button mBtnFinish;
    @Bind(R.id.btn_layout)
    FrameLayout mButtonsLayout;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.horizontal_divider)
    View divider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarder);
        ButterKnife.bind(this);
        evaluator = new ArgbEvaluator();
        mIbNext.setOnClickListener(this);
        mBtnSkip.setOnClickListener(this);
        mBtnFinish.setOnClickListener(this);
        mFab.setOnClickListener(this);
        mVpOnboarderPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (onboarderAdapter.getCount() - 1) && position < (mColors.length - 1)) {
                    mVpOnboarderPager.setBackgroundColor((Integer) evaluator.evaluate(positionOffset, mColors[position], mColors[position + 1]));
                    if (mShouldDarkenButtonsLayout) {
                        mButtonsLayout.setBackgroundColor(darkenColor((Integer) evaluator.evaluate(positionOffset, mColors[position], mColors[position + 1])));
                        divider.setVisibility(View.GONE);
                    }
                } else {
                    mVpOnboarderPager.setBackgroundColor(mColors[mColors.length - 1]);
                    if (mShouldDarkenButtonsLayout) {
                        mButtonsLayout.setBackgroundColor(darkenColor(mColors[mColors.length - 1]));
                        divider.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                int lastPagePosition = onboarderAdapter.getCount() - 1;
                mIbNext.setVisibility(position == lastPagePosition && !mShouldUseFloatingActionButton ? View.GONE : View.VISIBLE);
                mBtnFinish.setVisibility(position == lastPagePosition && !mShouldUseFloatingActionButton ? View.VISIBLE : View.GONE);
                if (mShouldUseFloatingActionButton)
                    mFab.setImageResource(position == lastPagePosition ? R.drawable.ic_done_white_24dp : R.drawable.ic_arrow_forward_white_24dp);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private int darkenColor(@ColorInt int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.9f;
        return Color.HSVToColor(hsv);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        boolean isInLastPage = mVpOnboarderPager.getCurrentItem() == onboarderAdapter.getCount() - 1;

        if (i == R.id.btn_next || i == R.id.fab && !isInLastPage) {
            mVpOnboarderPager.setCurrentItem(mVpOnboarderPager.getCurrentItem() + 1);
        } else if (i == R.id.btn_skip) {
            onSkipButtonPressed();
        } else if (i == R.id.btn_finish || i == R.id.fab && isInLastPage) {
            onFinishButtonPressed();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected void onSkipButtonPressed() {
        mVpOnboarderPager.setCurrentItem(onboarderAdapter.getCount());
    }

    abstract public void onFinishButtonPressed();

    public void setOnboardPagesReady(List<OnBoarder> pages) {
        onboarderAdapter = new OnBoarderAdapter(pages, getSupportFragmentManager());
        mVpOnboarderPager.setAdapter(onboarderAdapter);
        mColors = ColorsArrayBuilder.getPageBackgroundColors(this, pages);
        mStepperIndicatorView.setViewPager(mVpOnboarderPager, true);
    }

    public void shouldDarkenButtonsLayout(boolean shouldDarkenButtonsLayout) {
        this.mShouldDarkenButtonsLayout = shouldDarkenButtonsLayout;
    }

    public void setDividerColor(@ColorInt int color) {
        if (!this.mShouldDarkenButtonsLayout)
            this.divider.setBackgroundColor(color);
    }

    public void setDividerHeight(int heightInDp) {
        if (!this.mShouldDarkenButtonsLayout)
            this.divider.getLayoutParams().height =
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightInDp,
                            getResources().getDisplayMetrics());
    }

    public void setDividerVisibility(int dividerVisibility) {
        this.divider.setVisibility(dividerVisibility);
    }

    public void setSkipButtonTitle(CharSequence title) {
        this.mBtnSkip.setText(title);
    }

    public void setSkipButtonHidden() {
        this.mBtnSkip.setVisibility(View.GONE);
    }

    public void setSkipButtonTitle(@StringRes int titleResId) {
        this.mBtnSkip.setText(titleResId);
    }

    public void setFinishButtonTitle(CharSequence title) {
        this.mBtnFinish.setText(title);
    }

    public void setFinishButtonTitle(@StringRes int titleResId) {
        this.mBtnFinish.setText(titleResId);
    }
}
