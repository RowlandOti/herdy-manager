package co.herdy.manager.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import co.herdy.manager.presentation.authfeature.view.activity.AuthActivity;
import co.herdy.manager.presentation.onboarderfeature.view.activity.OnBoarderActivity;
import co.herdy.manager.presentation.userfeature.view.activity.UserDetailsActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
        //empty
    }


    /**
     * Goes to the user details screen.
     *
     * @param context A Context needed to open the destiny activity.
     * @param userKey   A User id.
     */
    public void navigateToUserDetails(Context context, String userKey) {
        if (context != null) {
            Intent intentToLaunch = UserDetailsActivity.getCallingIntent(context, userKey);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * Goes to the onboarding screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToOnBoarderActivity(Context context) {
        if (context != null) {
            Intent intentToLaunch = OnBoarderActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * Goes to the authentication screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToAuthActivity(Context context) {
        if (context != null) {
            Intent intentToLaunch = AuthActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * Goes to the dashboard screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToDashBoardActivity(Context context) {
        if (context != null) {
            Intent intentToLaunch = DashBoardActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
}
