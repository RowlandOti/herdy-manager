package co.herdy.manager.presentation.authfeature.view.activity;

import android.os.Bundle;

import co.herdy.manager.R;
import co.herdy.manager.presentation.view.activity.ABaseActivity;

/*
*  Login activity
* */
public class LoginActivity extends ABaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}