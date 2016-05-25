package co.herdy.manager.presentation.userfeature.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import co.herdy.manager.presentation.userfeature.view.fragment.UserDetailsFragment;
import co.herdy.manager.presentation.internal.di.HasComponent;
import co.herdy.manager.presentation.internal.di.coponents.UserComponent;
import co.herdy.manager.presentation.internal.di.modules.UserModule;
import co.herdy.manager.presentation.view.activity.ABaseActivity;

import co.herdy.manager.R;
import co.herdy.manager.presentation.internal.di.coponents.DaggerUserComponent;

/**
 * Activity that shows details of a certain user.
 */
public class UserDetailsActivity extends ABaseActivity implements HasComponent<UserComponent> {

  private static final String INTENT_EXTRA_PARAM_BID_ID = "co.herdy.manager.INTENT_PARAM_BID_ID";
  private static final String INSTANCE_STATE_PARAM_BID_ID = "co.herdy.manager.STATE_PARAM_BID_ID";

  public static Intent getCallingIntent(Context context, String userKey) {
    Intent callingIntent = new Intent(context, UserDetailsActivity.class);
    callingIntent.putExtra(INTENT_EXTRA_PARAM_BID_ID, userKey);
    return callingIntent;
  }

  private String userKey;
  private UserComponent userComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_details);

    this.initializeActivity(savedInstanceState);
    this.initializeInjector();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    if (outState != null) {
      outState.putString(INSTANCE_STATE_PARAM_BID_ID, this.userKey);
    }
    super.onSaveInstanceState(outState);
  }

  /**
   * Initializes this activity.
   */
  private void initializeActivity(Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      this.userKey = getIntent().getStringExtra(INTENT_EXTRA_PARAM_BID_ID);
      addFragment(R.id.fragmentContainer, new UserDetailsFragment(), true);
    } else {
      this.userKey = savedInstanceState.getString(INSTANCE_STATE_PARAM_BID_ID);
    }
  }

  private void initializeInjector() {
    this.userComponent = DaggerUserComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .userModule(new UserModule(this.userKey))
        .build();
  }

  @Override public UserComponent getComponent() {
    return userComponent;
  }
}
