package co.herdy.manager.presentation.authfeature.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import co.herdy.manager.R;
import co.herdy.manager.data.userfeature.payload.UserPayload;
import co.herdy.manager.presentation.ApplicationController;
import co.herdy.manager.presentation.authfeature.view.fragment.AuthLoginFragment;
import co.herdy.manager.presentation.authfeature.view.fragment.AuthRegisterFragment;
import co.herdy.manager.presentation.internal.di.HasComponent;
import co.herdy.manager.presentation.internal.di.components.AuthComponent;
import co.herdy.manager.presentation.internal.di.components.DaggerAuthComponent;
import co.herdy.manager.presentation.internal.di.modules.AuthModule;
import co.herdy.manager.presentation.view.activity.ABaseActivity;


public class AuthActivity extends ABaseActivity implements HasComponent<AuthComponent>, AuthRegisterFragment.onRegisterFinishBtnClickListener, AuthLoginFragment.onAuthLoginClickListener {

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
    public void onRegisterFinishClicked(Bundle args) {
        UserPayload payload = new UserPayload();
        payload.setUsername(args.getString(AuthActivity.AUTHUSERNAME));
        payload.setEmail(args.getString(AuthActivity.AUTHEMAIL));
        payload.setPassword(args.getString(AuthActivity.AUTHPASSWORD));
        ApplicationController.apiManager.registerUser(payload);
        showLoginFragment(args);
    }

    @Override
    public void onCallLoginClicked(Bundle args) {
        showLoginFragment(args);
    }

    @Override
    public void onLoginFinishClicked(Bundle args) {
        String email = args.getString(AuthActivity.AUTHEMAIL);
        String password = args.getString(AuthActivity.AUTHPASSWORD);
        ApplicationController.apiManager.loginUser(email, password);
        setResult(RESULT_OK, getIntent().putExtras(args));
        // finish();
    }

    @Override
    public void onCallRegisterClicked(Bundle args) {
        showRegisterFragment(args);
    }

    private void initializeInjector() {
        this.authComponent = DaggerAuthComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .authModule(new AuthModule(null, ))
                .build();
    }

    @Override
    public AuthComponent getComponent() {
        return authComponent;
    }
}