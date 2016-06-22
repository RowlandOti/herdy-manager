package co.herdy.manager.presentation.onboarderfeature.view.activity;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.view.ViewPager;
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

public abstract class AOnBoarderWithStepperIndicatorActivity extends ABaseActivity implements View.OnClickListener {

    private Integer[] mColors;
    private ArgbEvaluator evaluator;
    private OnBoarderAdapter onboarderAdapter;
    private boolean mShouldDarkenButtonsLayout = false;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarder);
        ButterKnife.bind(this);
        evaluator = new ArgbEvaluator();
        mIbNext.setOnClickListener(this);
        mBtnSkip.setOnClickListener(this);
        mBtnFinish.setOnClickListener(this);
        mVpOnboarderPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (onboarderAdapter.getCount() - 1) && position < (mColors.length - 1)) {
                    mVpOnboarderPager.setBackgroundColor((Integer) evaluator.evaluate(positionOffset, mColors[position], mColors[position + 1]));
                } else {
                    mVpOnboarderPager.setBackgroundColor(mColors[mColors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {
                int lastPagePosition = onboarderAdapter.getCount() - 1;
                mIbNext.setVisibility(position == lastPagePosition ? View.GONE : View.VISIBLE);
                mBtnSkip.setVisibility(position == lastPagePosition ? View.GONE : View.VISIBLE);
                mBtnFinish.setVisibility(position == lastPagePosition ? View.VISIBLE : View.GONE);
                if (position == lastPagePosition && mShouldDarkenButtonsLayout) {
                    mButtonsLayout.setBackgroundColor(getResources().getColor((R.color.app_color_accent_teal)));
                } else {
                    mButtonsLayout.setBackgroundColor(mColors[mColors.length - 1]);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        boolean isInLastPage = mVpOnboarderPager.getCurrentItem() == onboarderAdapter.getCount() - 1;

        if (i == R.id.btn_next || !isInLastPage) {
            mVpOnboarderPager.setCurrentItem(mVpOnboarderPager.getCurrentItem() + 1);
        } else if (i == R.id.btn_skip) {
            onSkipButtonPressed();
        } else if (i == R.id.btn_finish || isInLastPage) {
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
