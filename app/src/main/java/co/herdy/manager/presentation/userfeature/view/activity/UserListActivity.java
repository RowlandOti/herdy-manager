package co.herdy.manager.presentation.userfeature.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import co.herdy.manager.presentation.userfeature.view.fragment.UserListFragment;
import co.herdy.manager.presentation.internal.di.HasComponent;
import co.herdy.manager.presentation.internal.di.components.UserComponent;

import co.herdy.manager.R;
import co.herdy.manager.presentation.internal.di.components.DaggerUserComponent;
import co.herdy.manager.presentation.view.activity.ABaseActivity;

/**
 * Activity that shows a list of Users.
 */
public class UserListActivity extends ABaseActivity implements HasComponent<UserComponent>, UserListFragment.UserListListener {

    // Class log identifier
    public final static String LOG_TAG = UserListActivity.class.getSimpleName();

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, UserListActivity.class);
    }

    private UserComponent userComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new UserListFragment(), true);
        }
    }

    private void initializeInjector() {
        this.userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }

    @Override
    public void onUserClicked(String userModel) {
        this.navigator.navigateToUserDetails(this, userModel);
    }
}
