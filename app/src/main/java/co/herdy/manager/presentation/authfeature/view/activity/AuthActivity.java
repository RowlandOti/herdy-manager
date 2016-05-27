package co.herdy.manager.presentation.authfeature.view.activity;

import android.os.Bundle;

import co.herdy.manager.R;
import co.herdy.manager.data.userfeature.payload.UserPayload;
import co.herdy.manager.presentation.ApplicationController;
import co.herdy.manager.presentation.authfeature.view.fragment.LoginUserFragment;
import co.herdy.manager.presentation.authfeature.view.fragment.RegisterUserFragment;
import co.herdy.manager.presentation.view.activity.ABaseActivity;


public class AuthActivity extends ABaseActivity implements RegisterUserFragment.onRegisterFinishBtnClickListener, LoginUserFragment.onLoginFinishBtnClickListener {

    // Class log identifier
    public final static String LOG_TAG = AuthActivity.class.getSimpleName();

    public static String AUTHEMAIL = "AUTH.EMAIL";
    public static String AUTHUSERNAME = "AUTH.USERNAME";
    public static String AUTHPASSWORD = "AUTH.PASSWORD";

    private String mAuthToken = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        setStatusbarTransparent(true);
        // Check that the activity is using the layout with the fragment_container id
        if (findViewById(R.id.fragment_container) != null) {
            // However, if we're being restored from a previous state, then we don't need to do
            // anything and should return or else we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            } else {
                // Pass bundle to the fragment
                showLoginFragment(null);
            }
        }
    }

    private void showLoginFragment(Bundle args) {
        LoginUserFragment fragment = LoginUserFragment.newInstance(args);
        replaceFragment(R.id.fragment_container, fragment, false, true);
    }

    private void showRegisterFragment(Bundle args) {
        RegisterUserFragment fragment = RegisterUserFragment.newInstance(args);
        replaceFragment(R.id.fragment_container, fragment, false, true);
    }

    @Override
    public void onRegisterFinishClicked(Bundle args) {
        UserPayload payload = new UserPayload();
        payload.setUsername(args.getString(AuthActivity.AUTHUSERNAME));
        payload.setEmail(args.getString(AuthActivity.AUTHEMAIL));
        payload.setPassword(args.getString(AuthActivity.AUTHPASSWORD));
        ApplicationController.apiManager.register(payload);
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
        ApplicationController.apiManager.login(email, password);
        setResult(RESULT_OK, getIntent().putExtras(args));
        finish();
    }

    @Override
    public void onCallRegisterClicked(Bundle args) {
        showRegisterFragment(args);
    }
}