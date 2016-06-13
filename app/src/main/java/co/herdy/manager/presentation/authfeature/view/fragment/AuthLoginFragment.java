package co.herdy.manager.presentation.authfeature.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.herdy.manager.R;
import co.herdy.manager.presentation.authfeature.presenter.AuthLoginPresenter;
import co.herdy.manager.presentation.authfeature.view.IAuthLoginView;
import co.herdy.manager.presentation.authfeature.view.activity.AuthActivity;
import co.herdy.manager.presentation.internal.di.components.AuthComponent;
import co.herdy.manager.presentation.view.fragment.ABaseFragment;

/**
 * A simple {@link ABaseFragment} subclass.
 */
public class AuthLoginFragment extends ABaseFragment implements IAuthLoginView {

    // Class log identifier
    public final static String LOG_TAG = AuthLoginFragment.class.getSimpleName();

    private onAuthLoginClickListener mOnAuthLoginClickListener;

    public interface onAuthLoginClickListener {
        void onLoginFinishClicked(Bundle args);

        void onCallRegisterClicked(Bundle args);
    }

    @Inject
    AuthLoginPresenter authLoginPresenter;

    @Bind(R.id.img_logcover)
    ImageView ivLogcover;
    @Bind(R.id.et_email)
    TextInputEditText etEmail;
    @Bind(R.id.et_password)
    TextInputEditText etPassword;
    @Bind(R.id.rl_progress)
    RelativeLayout rlProgress;
    @Bind(R.id.rl_retry)
    RelativeLayout rlRetry;
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.tv_register)
    TextView tvRegister;

    public AuthLoginFragment() {
        setRetainInstance(true);
    }

    public static AuthLoginFragment newInstance(@Nullable Bundle args) {
        AuthLoginFragment fragmentInstance = new AuthLoginFragment();
        if (args != null) {
            fragmentInstance.setArguments(args);
        }
        return fragmentInstance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(AuthComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View fragmentView = inflater.inflate(R.layout.fragment_login_user, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.authLoginPresenter.setView(this);

        btLogin.setOnClickListener((View v) -> {
            if (!isValidEditTextData(etEmail, etPassword)) {
                return;
            }
            loginUser();
        });

        tvRegister.setOnClickListener((View v) -> {
            callRegisterView();
        });

        if (getArguments() != null) {
            etEmail.setText(getArguments().getString(AuthActivity.AUTHEMAIL));
            etPassword.setText(getArguments().getString(AuthActivity.AUTHPASSWORD));
            btLogin.performClick();
        }
    }

    // Called after fragment is attached to activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnAuthLoginClickListener = (onAuthLoginClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onAuthLoginClickListener");
        }
    }

    // Called after fragment is detached from activity
    @Override
    public void onDetach() {
        super.onDetach();
        mOnAuthLoginClickListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.authLoginPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.authLoginPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.authLoginPresenter.destroy();
    }

    @Override
    public void loginUser() {
        this.authLoginPresenter.initialize();
        if (this.mOnAuthLoginClickListener != null) {
            Bundle args = new Bundle();
            args.putString(AuthActivity.AUTHEMAIL, etEmail.getText().toString().trim());
            args.putString(AuthActivity.AUTHPASSWORD, etPassword.getText().toString().trim());
            this.mOnAuthLoginClickListener.onLoginFinishClicked(args);
        }
    }

    @Override
    public void callRegisterView() {
        if (this.mOnAuthLoginClickListener != null) {
            Bundle args = new Bundle();
            args.putString(AuthActivity.AUTHEMAIL, etEmail.getText().toString().trim());
            mOnAuthLoginClickListener.onCallRegisterClicked(args);
        }
    }

    @Override
    public void showLoading() {
        this.rlProgress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.rlProgress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }
}
