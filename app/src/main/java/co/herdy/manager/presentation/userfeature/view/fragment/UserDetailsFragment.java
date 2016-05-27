package co.herdy.manager.presentation.userfeature.view.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Activity;

import co.herdy.manager.R;
import co.herdy.manager.presentation.userfeature.model.UserModel;
import co.herdy.manager.presentation.userfeature.presenter.UserDetailsPresenter;
import co.herdy.manager.presentation.userfeature.view.IUserDetailsView;
import co.herdy.manager.presentation.userfeature.view.activity.UserDetailsActivity;
import co.herdy.manager.presentation.internal.di.components.UserComponent;
import co.herdy.manager.presentation.view.fragment.ABaseFragment;

import java.util.Random;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows details of a certain user.
 */
public class UserDetailsFragment extends ABaseFragment implements IUserDetailsView {

    @Inject
    UserDetailsPresenter userDetailsPresenter;

    @Nullable
    @Bind(R.id.tb_user)
    Toolbar mToolbar;
    @Bind(R.id.iv_cover)
    ImageView iv_cover;
    @Bind(R.id.tv_value)
    TextView tv_value;
    @Bind(R.id.tv_key)
    TextView tv_key;
    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;
    @Bind(R.id.rl_retry)
    RelativeLayout rl_retry;
    @Bind(R.id.bt_retry)
    Button bt_retry;

    public UserDetailsFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(UserComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_user_details, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.userDetailsPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadUserDetails();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.userDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.userDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.userDetailsPresenter.destroy();
    }

    @Override
    public void renderUser(UserModel user) {
        if (user != null) {
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            this.iv_cover.setBackgroundColor(color);
            this.tv_key.setText(user.getUserId());
            this.tv_value.setText(user.getUsername());
        }
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    /**
     * Loads all users.
     */
    private void loadUserDetails() {
        if (this.userDetailsPresenter != null) {
            this.userDetailsPresenter.initialize();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Set the ToolBar
        ((UserDetailsActivity) getActivity()).setToolbar(mToolbar, true, true, 0);
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        UserDetailsFragment.this.loadUserDetails();
    }
}
