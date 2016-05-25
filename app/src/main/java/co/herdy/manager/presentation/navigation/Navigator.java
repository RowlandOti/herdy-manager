package co.herdy.manager.presentation.navigation;

import android.content.Context;
import android.content.Intent;

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
}
