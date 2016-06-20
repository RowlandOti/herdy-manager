package co.herdy.manager.presentation.dashboardfeature.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.herdy.manager.R;

public class DashBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, DashBoardActivity.class);
    }
}
