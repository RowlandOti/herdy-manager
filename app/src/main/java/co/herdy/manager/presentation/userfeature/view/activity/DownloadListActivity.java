package co.herdy.manager.presentation.userfeature.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import co.herdy.manager.presentation.userfeature.view.fragment.DownloadListFragment;
import co.herdy.manager.presentation.internal.di.HasComponent;
import co.herdy.manager.presentation.internal.di.coponents.DownloadComponent;

import co.herdy.manager.R;
import co.herdy.manager.presentation.internal.di.coponents.DaggerDownloadComponent;
import co.herdy.manager.presentation.view.activity.ABaseActivity;

/**
 * Activity that shows a list of Downloads.
 */
public class DownloadListActivity extends ABaseActivity implements HasComponent<DownloadComponent>, DownloadListFragment.DownloadListListener {

    // Class log identifier
    public final static String LOG_TAG = DownloadListActivity.class.getSimpleName();

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, DownloadListActivity.class);
    }

    private DownloadComponent userComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new DownloadListFragment(), true);
        }
    }

    private void initializeInjector() {
        this.userComponent = DaggerDownloadComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public DownloadComponent getComponent() {
        return userComponent;
    }

    @Override
    public void onDownloadClicked(String userModel) {
        this.navigator.navigateToDownloadDetails(this, userModel);
    }
}
