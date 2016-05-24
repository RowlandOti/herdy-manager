package co.herdy.manager.presentation.userfeature.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import co.herdy.manager.presentation.userfeature.view.fragment.DownloadDetailsFragment;
import co.herdy.manager.presentation.internal.di.HasComponent;
import co.herdy.manager.presentation.internal.di.coponents.DownloadComponent;
import co.herdy.manager.presentation.internal.di.modules.DownloadModule;
import co.herdy.manager.presentation.view.activity.ABaseActivity;

import co.herdy.manager.R;
import co.herdy.manager.presentation.internal.di.coponents.DaggerDownloadComponent;

/**
 * Activity that shows details of a certain user.
 */
public class DownloadDetailsActivity extends ABaseActivity implements HasComponent<DownloadComponent> {

  private static final String INTENT_EXTRA_PARAM_BID_ID = "co.herdy.manager.INTENT_PARAM_BID_ID";
  private static final String INSTANCE_STATE_PARAM_BID_ID = "co.herdy.manager.STATE_PARAM_BID_ID";

  public static Intent getCallingIntent(Context context, String userKey) {
    Intent callingIntent = new Intent(context, DownloadDetailsActivity.class);
    callingIntent.putExtra(INTENT_EXTRA_PARAM_BID_ID, userKey);
    return callingIntent;
  }

  private String userKey;
  private DownloadComponent userComponent;

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
      addFragment(R.id.fragmentContainer, new DownloadDetailsFragment(), true);
    } else {
      this.userKey = savedInstanceState.getString(INSTANCE_STATE_PARAM_BID_ID);
    }
  }

  private void initializeInjector() {
    this.userComponent = DaggerDownloadComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .userModule(new DownloadModule(this.userKey))
        .build();
  }

  @Override public DownloadComponent getComponent() {
    return userComponent;
  }
}
