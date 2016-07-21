package co.herdy.manager.presentation.dashboardfeature.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.herdy.manager.R;
import co.herdy.manager.presentation.dashboardfeature.view.fragment.AddAnimalFragment;
import co.herdy.manager.presentation.dashboardfeature.view.fragment.CalendarFragment;
import co.herdy.manager.presentation.view.activity.ABaseActivity;

public class DashBoardActivity extends ABaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Logging Identifier for class
    private final String LOG_TAG = DashBoardActivity.class.getSimpleName();

    // The Food POSITION Identifier Key
    public static final String SELECTED_NAV_MENU_KEY = "selected_nav_menu_key";
    // The selected grid position
    private int mSelectedNavMenuIndex = 0;
    // Navigation Header
    private View navigationHeader;
    // Accounts toggle
    private boolean toggle = false;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.fab_dashboard)
    FloatingActionButton fabButton;
    @Bind(R.id.appbar)
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        setToolbar(false, true, 0);
        setStatusbarTransparent(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState != null) {
            // Recover assets
            mSelectedNavMenuIndex = savedInstanceState.getInt(SELECTED_NAV_MENU_KEY);
            // Recover menu as selected
            MenuItem menuItem = navigationView.getMenu().getItem(mSelectedNavMenuIndex);
            onNavigationItemSelected(menuItem);
            return;
        } else {
            MenuItem menuItem = navigationView.getMenu().getItem(mSelectedNavMenuIndex);
            onNavigationItemSelected(menuItem);
        }
        setListeners();
    }

    // Save any important data for recovery
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_NAV_MENU_KEY, mSelectedNavMenuIndex);
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, DashBoardActivity.class);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_calendar:
                showCalendarFragment(null);
                mSelectedNavMenuIndex = 0;
                break;
            case R.id.nav_animals:
                mSelectedNavMenuIndex = 1;
                break;
            case R.id.nav_litters:
                mSelectedNavMenuIndex = 2;
                break;
            case R.id.nav_breeding:
                mSelectedNavMenuIndex = 3;
                break;
            case R.id.nav_preferences:
                mSelectedNavMenuIndex = 4;
                break;
            case R.id.nav_logout:
                break;
            default:
                showCalendarFragment(null);
                mSelectedNavMenuIndex = 0;
        }
        toggleNavMenuItemCheck(item);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void showCalendarFragment(Bundle args) {
        CalendarFragment fragment = CalendarFragment.newInstance(args);
        replaceFragment(R.id.fragment_container, fragment, false, true);
    }

    private void toggleNavMenuItemCheck(MenuItem menuItem) {
        if (menuItem.isChecked()) {
            menuItem.setChecked(false);
        } else {
            menuItem.setChecked(true);
        }
    }

    private void setListeners() {
        navigationHeader = navigationView.getHeaderView(0);
        ImageView imageViewCarret = (ImageView) navigationHeader.findViewById(R.id.imageViewCarret);
        ImageView imageViewProduct = (ImageView) navigationHeader.findViewById(R.id.imageViewProduct);
        imageViewCarret.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (toggle) {
                    imageViewCarret.setImageResource(R.drawable.ic_arrow_drop_up_white_48dp);
                    toggle = false;
                    navigationView.getMenu().clear();
                    navigationView.inflateMenu(R.menu.activity_dashboard_drawer);
                    if(navigationHeader != null) {
                        //navigationHeader.setBackgroundResource(R.drawable.side_nav_bar);
                    }
                } else {
                    imageViewCarret.setImageResource(R.drawable.ic_arrow_drop_down_white_48dp);
                    toggle = true;
                    navigationView.getMenu().clear();
                    navigationView.inflateMenu(R.menu.activity_dashboard_drawer_account_selector);
                    if(navigationHeader != null) {
                        //navigationHeader.setBackgroundResource(R.color.app_color_grey);
                    }
                }
            }
        });
    }

    public AppBarLayout getAppBarLayout() {
        return appBarLayout;
    }

    @OnClick(R.id.fab_dashboard)
    public void onClickFab() {
        AddAnimalFragment fragment = AddAnimalFragment.newInstance(null);
        replaceFragment(R.id.fragment_add_container, fragment, false, true);
    }
}
