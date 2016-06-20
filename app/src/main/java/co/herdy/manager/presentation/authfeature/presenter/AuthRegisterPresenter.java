package co.herdy.manager.presentation.authfeature.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import co.herdy.manager.domain.authfeature.usecase.AuthRegisterUseCase;
import co.herdy.manager.domain.exception.DefaultErrorBundle;
import co.herdy.manager.domain.exception.IErrorBundle;
import co.herdy.manager.domain.interactor.DefaultSubscriber;
import co.herdy.manager.domain.interactor.UseCase;
import co.herdy.manager.presentation.authfeature.view.IAuthRegisterView;
import co.herdy.manager.presentation.authfeature.view.activity.AuthActivity;
import co.herdy.manager.presentation.exception.ErrorMessageFactory;
import co.herdy.manager.presentation.internal.di.PerActivity;
import co.herdy.manager.presentation.presenter.IPresenter;

@PerActivity
public class AuthRegisterPresenter implements IPresenter {

    // Class log identifier
    public final static String LOG_TAG = AuthRegisterPresenter.class.getSimpleName();

    private IAuthRegisterView authRegisterView;
    private final UseCase authRegisterUseCase;

    @Inject
    public AuthRegisterPresenter(@Named("authRegisterUser") UseCase authRegisterUseCase) {
        this.authRegisterUseCase = authRegisterUseCase;
    }

    public void setView(@NonNull IAuthRegisterView view) {
        this.authRegisterView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.authRegisterUseCase.unsubscribe();
        this.authRegisterView = null;
    }

    /**
     * Initializes the presenter
     */
    public void initializeRegistration(String... params) {
        this.showViewLoading();
        this.authRegister(params);
    }

    private void showViewLoading() {
        this.authRegisterView.showLoading();
    }

    private void hideViewLoading() {
        this.authRegisterView.hideLoading();
    }

    private void showErrorMessage(IErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.authRegisterView.context(), errorBundle.getException());
        this.authRegisterView.showError(errorMessage);
    }

    private void authRegister(String[] params) {
        ((AuthRegisterUseCase) this.authRegisterUseCase).init(params[0], params[1], params[2]);
        this.authRegisterUseCase.execute(new AuthRegisterSubscriber(params));
    }

    private final class AuthRegisterSubscriber extends DefaultSubscriber<List<String>> {

        Bundle nArgs;

        public AuthRegisterSubscriber(String[] registrationParams) {
            super();
            Bundle args = new Bundle();
            args.putString(AuthActivity.AUTHEMAIL, registrationParams[0]);
            args.putString(AuthActivity.AUTHPASSWORD, registrationParams[1]);
            this.nArgs = args;
        }

        @Override
        public void onCompleted() {
            AuthRegisterPresenter.this.hideViewLoading();
            authRegisterView.getViewListener().onRegisterFinish(this.nArgs);
        }

        @Override
        public void onError(Throwable e) {
            AuthRegisterPresenter.this.hideViewLoading();
            AuthRegisterPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onNext(List<String> userToken) {
            // AuthRegisterPresenter.this.showUsersCollectionInView(users);
        }
    }
}
