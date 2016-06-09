package co.herdy.manager.presentation.onboarderfeature.view.activity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import co.herdy.manager.R;
import co.herdy.manager.data.splashfeature.preference.SplashPreferenceManager;
import co.herdy.manager.presentation.authfeature.view.activity.AuthActivity;
import co.herdy.manager.presentation.onboarderfeature.view.fragment.OnBoarder;
import co.herdy.manager.presentation.splashfeature.view.activity.SplashActivity;

public class OnBoarderActivity extends AOnBoarderActivity {

    // Class log identifier
    public final static String LOG_TAG = SplashActivity.class.getSimpleName();

    private List<OnBoarder> onBoarderPages;
    private SplashPreferenceManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBoarderPages = new ArrayList<>();
        prefManager = new SplashPreferenceManager(this);

        OnBoarder intro1 = new OnBoarder("Welcome", "Your Two Steps Away from Making Farming Easier", R.drawable.herdy_logo_125px);
        intro1.setBackgroundColor(R.color.app_color_primary);
        intro1.setDescriptionColor(R.color.app_color_white);
        intro1.setTitleColor(R.color.app_color_white);

        onBoarderPages.add(intro1);
        onBoarderPages.add(intro1);
        setOnboardPagesReady(onBoarderPages);
        shouldDarkenButtonsLayout(true);
    }

    @Override
    public void onSkipButtonPressed() {
        super.onSkipButtonPressed();
        loadAuthActivity();
    }

    @Override
    public void onFinishButtonPressed() {
        loadAuthActivity();
    }

    private void loadAuthActivity() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        prefManager.setIsFirstTimeLaunch(false);
        animateActivityTransition();
        finish();
    }
}
