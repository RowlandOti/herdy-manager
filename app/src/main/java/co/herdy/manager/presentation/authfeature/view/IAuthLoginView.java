package co.herdy.manager.presentation.authfeature.view;

import co.herdy.manager.presentation.view.ILoadDataView;

public interface IAuthLoginView extends ILoadDataView {

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
}
