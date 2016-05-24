package co.herdy.manager.presentation.userfeature.presenter;

import android.support.annotation.NonNull;

import co.herdy.manager.domain.interactor.DefaultSubscriber;
import co.herdy.manager.domain.interactor.UseCase;
import co.herdy.manager.presentation.userfeature.mapper.DownloadModelDataMapper;
import co.herdy.manager.presentation.exception.ErrorMessageFactory;
import co.herdy.manager.presentation.internal.di.PerActivity;
import co.herdy.manager.domain.exception.DefaultErrorBundle;
import co.herdy.manager.domain.exception.IErrorBundle;
import co.herdy.manager.presentation.userfeature.view.IDownloadListView;
import co.herdy.manager.presentation.presenter.IPresenter;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link IPresenter} that controls comunication between views and models of the presentation
 * layer.
 */
@PerActivity
public class DownloadListPresenter implements IPresenter {

    // Class log identifier
    public final static String LOG_TAG = DownloadListPresenter.class.getSimpleName();

    private IDownloadListView viewListView;

    private final UseCase getDownloadListUseCase;
    private final DownloadModelDataMapper userModelDataMapper;

    @Inject
    public DownloadListPresenter(@Named("userList") UseCase getDownloadListDownloadCase, DownloadModelDataMapper userModelDataMapper) {
        this.getDownloadListUseCase = getDownloadListDownloadCase;
        this.userModelDataMapper = userModelDataMapper;
    }

    public void setView(@NonNull IDownloadListView view) {
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
        this.getDownloadListUseCase.unsubscribe();
        this.viewListView = null;
    }

    /**
     * Initializes the presenter by start retrieving the user list.
     */
    public void initialize() {
        this.loadDownloadList();
    }

    /**
     * Loads all users.
     */
    private void loadDownloadList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getDownloadList();
    }

    public void onDownloadClicked(String userModel) {
        this.viewListView.viewDownload(userModel);
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

    private void showDownloadsCollectionInView(Collection<String> usersCollection) {
        this.viewListView.renderDownloadList(usersCollection);
    }

    private void getDownloadList() {
        this.getDownloadListUseCase.execute(new DownloadListSubscriber());
    }

    private final class DownloadListSubscriber extends DefaultSubscriber<List<String>> {

        @Override
        public void onCompleted() {
            DownloadListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            DownloadListPresenter.this.hideViewLoading();
            DownloadListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            DownloadListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<String> users) {
            DownloadListPresenter.this.showDownloadsCollectionInView(users);
        }
    }
}
