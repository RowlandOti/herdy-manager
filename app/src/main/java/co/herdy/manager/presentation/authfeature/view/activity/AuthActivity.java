package co.herdy.manager.presentation.authfeature.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import co.herdy.manager.R;
import co.herdy.manager.presentation.authfeature.view.fragment.AuthLoginFragment;
import co.herdy.manager.presentation.authfeature.view.fragment.AuthRegisterFragment;
import co.herdy.manager.presentation.internal.di.HasComponent;
import co.herdy.manager.presentation.internal.di.components.AuthComponent;
import co.herdy.manager.presentation.internal.di.components.DaggerAuthComponent;
import co.herdy.manager.presentation.internal.di.modules.AuthModule;
import co.herdy.manager.presentation.view.activity.ABaseActivity;


public class AuthActivity extends ABaseActivity implements HasComponent<AuthComponent>, AuthRegisterFragment.onRegisterFinishBtnClickListener, AuthLoginFragment.OnAuthLoginClickListener {

    // Class log identifier
    public final static String LOG_TAG = AuthActivity.class.getSimpleName();

    public static String AUTHEMAIL = "AUTH.EMAIL";
    public static String AUTHUSERNAME = "AUTH.USERNAME";
    public static String AUTHPASSWORD = "AUTH.PASSWORD";

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, AuthActivity.class);
    }

    private AuthComponent authComponent;
    private String mAuthToken = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        this.setStatusbarTransparent(true);
        this.initializeInjector();
        // Check that the activity is using the layout with the fragment_container id
        if (findViewById(R.id.fragment_container) != null) {
            // Return or else we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            } else {
                // Pass bundle to the fragment
                showLoginFragment(null);
            }
        }
    }

    private void showLoginFragment(Bundle args) {
        AuthLoginFragment fragment = AuthLoginFragment.newInstance(args);
        replaceFragment(R.id.fragment_container, fragment, false, true);
    }

    private void showRegisterFragment(Bundle args) {
        AuthRegisterFragment fragment = AuthRegisterFragment.newInstance(args);
        replaceFragment(R.id.fragment_container, fragment, false, true);
    }

    @Override
    public void onCallLoginClicked(Bundle args) {
        showLoginFragment(args);
    }

    @Override
    public void onCallRegisterClicked(Bundle args) {
        showRegisterFragment(args);
    }

    @Override
    public void onLoginFinish(Bundle args) {

    }

    @Override
    public void onRegisterFinish(Bundle args) {
        showLoginFragment(args);
    }

    private void initializeInjector() {
        this.authComponent = DaggerAuthComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .authModule(new AuthModule())
                .build();
    }

    @Override
    public AuthComponent getComponent() {
        return authComponent;
    }
}