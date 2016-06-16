package co.herdy.manager.presentation.authfeature.view;

import android.os.Bundle;

import co.herdy.manager.presentation.view.ILoadDataView;

public interface IAuthRegisterView extends ILoadDataView {

    interface OnAuthViewClickListener {
        void onRegisterFinish(Bundle args);
    }

    /**
     * Register User
     *
     * @param
     */
    void registerUser();

    /**
     * Call User registerUser view
     *
     * @param
     */
    void callLoginView();

    /**
     * Call for Next Action
     */
    OnAuthViewClickListener getViewListener();
}
