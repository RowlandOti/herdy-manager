package co.herdy.manager.presentation.userfeature.presenter;

import android.support.annotation.NonNull;

import co.herdy.manager.domain.interactor.DefaultSubscriber;
import co.herdy.manager.domain.interactor.UseCase;
import co.herdy.manager.presentation.userfeature.mapper.UserModelDataMapper;
import co.herdy.manager.presentation.exception.ErrorMessageFactory;
import co.herdy.manager.presentation.internal.di.PerActivity;
import co.herdy.manager.domain.exception.DefaultErrorBundle;
import co.herdy.manager.domain.exception.IErrorBundle;
import co.herdy.manager.presentation.userfeature.view.IUserListView;
import co.herdy.manager.presentation.presenter.IPresenter;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link IPresenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class UserListPresenter implements IPresenter {

    // Class log identifier
    public final static String LOG_TAG = UserListPresenter.class.getSimpleName();

    private IUserListView viewListView;

    private final UseCase getUserListUseCase;
    private final UserModelDataMapper userModelDataMapper;

    @Inject
    public UserListPresenter(@Named("userList") UseCase getUserListUserCase, UserModelDataMapper userModelDataMapper) {
        this.getUserListUseCase = getUserListUserCase;
        this.userModelDataMapper = userModelDataMapper;
    }

    public void setView(@NonNull IUserListView view) {
        this.viewListView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getUserListUseCase.unsubscribe();
        this.viewListView = null;
    }

    /**
     * Initializes the presenter by start retrieving the user list.
     */
    public void initialize() {
        this.loadUserList();
    }

    /**
     * Loads all users.
     */
    private void loadUserList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getUserList();
    }

    public void onUserClicked(String userModel) {
        this.viewListView.viewUser(userModel);
    }

    private void showViewLoading() {
        this.viewListView.showLoading();
    }

    private void hideViewLoading() {
        this.viewListView.hideLoading();
    }

    private void showViewRetry() {
        this.viewListView.showRetry();
    }

    private void hideViewRetry() {
        this.viewListView.hideRetry();
    }

    private void showErrorMessage(IErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
                errorBundle.getException());
        this.viewListView.showError(errorMessage);
    }

    private void showUsersCollectionInView(Collection<String> usersCollection) {
        this.viewListView.renderUserList(usersCollection);
    }

    private void getUserList() {
        this.getUserListUseCase.execute(new UserListSubscriber());
    }

    private final class UserListSubscriber extends DefaultSubscriber<List<String>> {

        @Override
        public void onCompleted() {
            UserListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            UserListPresenter.this.hideViewLoading();
            UserListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            UserListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<String> users) {
            UserListPresenter.this.showUsersCollectionInView(users);
        }
    }
}
