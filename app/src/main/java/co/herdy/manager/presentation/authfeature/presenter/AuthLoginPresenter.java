package co.herdy.manager.presentation.authfeature.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import co.herdy.manager.domain.authfeature.usecase.AuthLoginUseCase;
import co.herdy.manager.domain.exception.DefaultErrorBundle;
import co.herdy.manager.domain.exception.IErrorBundle;
import co.herdy.manager.domain.interactor.DefaultSubscriber;
import co.herdy.manager.domain.interactor.UseCase;
import co.herdy.manager.presentation.authfeature.view.IAuthLoginView;
import co.herdy.manager.presentation.exception.ErrorMessageFactory;
import co.herdy.manager.presentation.internal.di.PerActivity;
import co.herdy.manager.presentation.presenter.IPresenter;

@PerActivity
public class AuthLoginPresenter implements IPresenter {

    // Class log identifier
    public final static String LOG_TAG = AuthLoginPresenter.class.getSimpleName();

    private IAuthLoginView authLoginView;
    private final UseCase authLoginUseCase;

    @Inject
    public AuthLoginPresenter(@Named("authLoginUser") UseCase authLoginUseCase) {
        this.authLoginUseCase = authLoginUseCase;
    }

    public void setView(@NonNull IAuthLoginView view) {
        this.authLoginView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.authLoginUseCase.unsubscribe();
        this.authLoginView = null;
    }

    /**
     * Initializes the presenter
     */
    public void initializeLogin(String... params) {
        this.showViewLoading();
        this.authLogin(params);
    }

    private void showViewLoading() {
        this.authLoginView.showLoading();
    }

    private void hideViewLoading() {
        this.authLoginView.hideLoading();
    }

    private void showErrorMessage(IErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.authLoginView.context(), errorBundle.getException());
        this.authLoginView.showError(errorMessage);
    }

    private void authLogin(String... params) {
        ((AuthLoginUseCase) this.authLoginUseCase).init(params[0], params[1]);
        this.authLoginUseCase.execute(new AuthLoginSubscriber());
    }

    private final class AuthLoginSubscriber extends DefaultSubscriber<List<String>> {

        public AuthLoginSubscriber() {
            super();
        }

        @Override
        public void onCompleted() {
            AuthLoginPresenter.this.hideViewLoading();
            authLoginView.getViewListener().onLoginFinish(null);
        }

        @Override
        public void onError(Throwable e) {
            AuthLoginPresenter.this.hideViewLoading();
            AuthLoginPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));

            authLoginView.getViewListener().onLoginFinish(null);
        }

        @Override
        public void onNext(List<String> userToken) {
            // AuthLoginPresenter.this.showUsersCollectionInView(users);
        }
    }
}
