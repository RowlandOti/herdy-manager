package com.doea.app.presentation.authfeature.view.activity;

import android.os.Bundle;

import com.doea.app.presentation.R;
import com.doea.app.presentation.view.activity.ABaseActivity;

public class LoginActivity extends ABaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}