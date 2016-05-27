package co.herdy.manager.presentation.authfeature.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
public class LoginUserFragment extends ABaseFragment {

    // Class log identifier
    public final static String LOG_TAG = LoginUserFragment.class.getSimpleName();

    private onLoginFinishBtnClickListener mLoginFinishBtnClickListener;

    public interface onLoginFinishBtnClickListener {
        void onLoginFinishClicked(Bundle args);

        void onCallRegisterClicked(Bundle args);
    }

    @Bind(R.id.img_logcover)
    ImageView ivLogcover;
    @Bind(R.id.et_email)
    EditText etEmail;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.rl_progress)
    RelativeLayout rlProgress;
    @Bind(R.id.rl_retry)
    RelativeLayout rlRetry;
    @Bind(R.id.bt_retry)
    Button btRetry;
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.tv_register)
    TextView tvRegister;

    public LoginUserFragment() {
        setRetainInstance(true);
    }

    // Actual method to use to create new fragment instance externally
    public static LoginUserFragment newInstance(@Nullable Bundle args) {
        LoginUserFragment fragmentInstance = new LoginUserFragment();
        if (args != null) {
            fragmentInstance.setArguments(args);
        }
        return fragmentInstance;
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

        if (getArguments() != null) {
            etEmail.setText(getArguments().getString(AuthActivity.AUTHEMAIL));
            etPassword.setText(getArguments().getString(AuthActivity.AUTHPASSWORD));
        }
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidEditTextData(etEmail, etPassword)) {
                    return;
                }
                Bundle args = new Bundle();
                args.putString(AuthActivity.AUTHEMAIL, etEmail.getText().toString().trim());
                args.putString(AuthActivity.AUTHPASSWORD, etPassword.getText().toString().trim());
                mLoginFinishBtnClickListener.onLoginFinishClicked(args);
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString(AuthActivity.AUTHEMAIL, etEmail.getText().toString().trim());
                mLoginFinishBtnClickListener.onCallRegisterClicked(args);
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
            mLoginFinishBtnClickListener = (onLoginFinishBtnClickListener) context;
        } catch (ClassCastException e) {
            // If not, it throws an exception
            throw new ClassCastException(context.toString() + " must implement onLoginFinishBtnClickListener");
        }
    }

    // Called after fragment is detached from activity
    @Override
    public void onDetach() {
        // Avoid leaking,
        mLoginFinishBtnClickListener = null;
        super.onDetach();
    }
}
