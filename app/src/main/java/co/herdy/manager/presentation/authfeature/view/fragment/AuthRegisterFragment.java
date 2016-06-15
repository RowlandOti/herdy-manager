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

import butterknife.Bind;
import butterknife.ButterKnife;
import co.herdy.manager.R;
import co.herdy.manager.presentation.authfeature.view.activity.AuthActivity;
import co.herdy.manager.presentation.view.fragment.ABaseFragment;

/**
 * A simple {@link ABaseFragment} subclass.
 */
public class AuthRegisterFragment extends ABaseFragment {

    // Class log identifier
    public final static String LOG_TAG = AuthRegisterFragment.class.getSimpleName();

    private onRegisterFinishBtnClickListener mRegisterFinishBtnClickListener;

    public interface onRegisterFinishBtnClickListener {
        void onRegisterFinishClicked(Bundle args);

        void onCallLoginClicked(Bundle args);
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View fragmentView = inflater.inflate(R.layout.fragment_register_user, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidEditTextData(etUsername, etEmail, etPassword)) {
                    return;
                }
                Bundle args = new Bundle();
                args.putString(AuthActivity.AUTHUSERNAME, etUsername.getText().toString().trim());
                args.putString(AuthActivity.AUTHEMAIL, etEmail.getText().toString().trim());
                args.putString(AuthActivity.AUTHPASSWORD, etPassword.getText().toString().trim());
                mRegisterFinishBtnClickListener.onRegisterFinishClicked(args);
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString(AuthActivity.AUTHEMAIL, etEmail.getText().toString().trim());
                mRegisterFinishBtnClickListener.onCallLoginClicked(args);
            }
        });
    }

    // Called after fragment is attached to activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Ensure attached activity has implemented the callback interface.
        try {
            // Acquire the implemented callback
            mRegisterFinishBtnClickListener = (onRegisterFinishBtnClickListener) context;
        } catch (ClassCastException e) {
            // If not, it throws an exception
            throw new ClassCastException(context.toString() + " must implement onRegisterFinishBtnClickListener");
        }
    }

    // Called after fragment is detached from activity
    @Override
    public void onDetach() {
        // Avoid leaking,
        mRegisterFinishBtnClickListener = null;
        super.onDetach();
    }
}
