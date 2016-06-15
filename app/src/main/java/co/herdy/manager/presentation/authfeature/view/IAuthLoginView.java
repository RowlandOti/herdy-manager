package co.herdy.manager.presentation.authfeature.view;

import co.herdy.manager.presentation.view.ILoadDataView;
import co.herdy.manager.presentation.view.fragment.ABaseFragment;

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

    /**
     * Cause for Next Action
     *
     */
    ABaseFragment.OnViewListener getViewListener();
}
