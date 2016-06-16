package co.herdy.manager.presentation.authfeature.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.herdy.manager.R;
import co.herdy.manager.presentation.authfeature.presenter.AuthRegisterPresenter;
import co.herdy.manager.presentation.authfeature.view.IAuthRegisterView;
import co.herdy.manager.presentation.authfeature.view.activity.AuthActivity;
import co.herdy.manager.presentation.internal.di.components.AuthComponent;
import co.herdy.manager.presentation.view.fragment.ABaseFragment;

/**
 * A simple {@link ABaseFragment} subclass.
 */
public class AuthRegisterFragment extends ABaseFragment implements IAuthRegisterView {

    // Class log identifier
    public final static String LOG_TAG = AuthRegisterFragment.class.getSimpleName();

    private onAuthRegisterClickListener mOnAuthRegisterClickListener;

    public interface onAuthRegisterClickListener extends IAuthRegisterView.OnAuthViewClickListener {
        void onCallLoginClicked(Bundle args);
    }

    @Inject
    AuthRegisterPresenter authRegisterPresenter;

    @Bind(R.id.et_username)
    TextInputEditText etUsername;
    @Bind(R.id.et_email)
    TextInputEditText etEmail;
    @Bind(R.id.et_password)
    TextInputEditText etPassword;
    @Bind(R.id.rl_progress)
    RelativeLayout rlProgress;
    @Bind(R.id.rl_retry)
    RelativeLayout rlRetry;
    @Bind(R.id.bt_retry)
    Button btRetry;
    @Bind(R.id.bt_register)
    Button btRegister;
    @Bind(R.id.tv_login)
    TextView tvLogin;


    public AuthRegisterFragment() {
        setRetainInstance(true);
    }

    // Actual method to use to create new fragment instance externally
    public static AuthRegisterFragment newInstance(@Nullable Bundle args) {
        AuthRegisterFragment fragmentInstance = new AuthRegisterFragment();
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
        final View fragmentView = inflater.inflate(R.layout.fragment_register_user, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.authRegisterPresenter.setView(this);

        btRegister.setOnClickListener((View v) -> {
            if (!isValidEditTextData(etUsername, etEmail, etPassword)) {
                return;
            }
            registerUser();
        });

        tvLogin.setOnClickListener((View v) -> {
            callLoginView();
        });
    }

    // Called after fragment is attached to activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnAuthRegisterClickListener = (onAuthRegisterClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onAuthRegisterClickListener");
        }
    }

    // Called after fragment is detached from activity
    @Override
    public void onDetach() {
        mOnAuthRegisterClickListener = null;
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.authRegisterPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.authRegisterPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.authRegisterPresenter.destroy();
    }

    @Override
    public void registerUser() {
        if (this.mOnAuthRegisterClickListener != null) {
            this.authRegisterPresenter.initializeRegistration(etEmail.getText().toString().trim(), etPassword.getText().toString().trim(), etUsername.getText().toString().trim());
        }
    }

    @Override
    public void callLoginView() {
        if (this.mOnAuthRegisterClickListener != null) {
            Bundle args = new Bundle();
            args.putString(AuthActivity.AUTHEMAIL, etEmail.getText().toString().trim());
            mOnAuthRegisterClickListener.onCallLoginClicked(args);
        }
    }

    @Override
    public IAuthRegisterView.OnAuthViewClickListener getViewListener() {
        return mOnAuthRegisterClickListener;
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
