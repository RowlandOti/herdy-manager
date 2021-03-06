package co.herdy.manager.presentation.authfeature.view;

import android.os.Bundle;

import co.herdy.manager.presentation.view.ILoadDataView;

public interface IAuthLoginView extends ILoadDataView {

    interface OnAuthViewClickListener {
        void onLoginFinish(Bundle args);
    }

    /**
     * Login User
     *
     * @param
     */
    void loginUser();

    /**
     * Call User registerUser view
     *
     * @param
     */
    void callRegisterView();

    /**
     * Call for Next Action
     */
    OnAuthViewClickListener getViewListener();
}
