package co.herdy.manager.presentation.userfeature.presenter;

import android.support.annotation.NonNull;

import co.fernandocejas.frodo.annotation.RxLogSubscriber;
import co.herdy.manager.domain.interactor.DefaultSubscriber;
import co.herdy.manager.domain.interactor.UseCase;
import co.herdy.manager.presentation.userfeature.mapper.DownloadModelDataMapper;
import co.herdy.manager.presentation.userfeature.model.DownloadModel;
import co.herdy.manager.presentation.userfeature.view.IDownloadDetailsView;
import co.herdy.manager.presentation.exception.ErrorMessageFactory;
import co.herdy.manager.presentation.internal.di.PerActivity;
import co.herdy.manager.domain.exception.DefaultErrorBundle;
import co.herdy.manager.domain.exception.IErrorBundle;
import co.herdy.manager.domain.userfeature.model.Download;
import co.herdy.manager.presentation.presenter.IPresenter;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link IPresenter} that controls comunication between views and models of the presentation
 * layer.
 */
@PerActivity
public class DownloadDetailsPresenter implements IPresenter {

  private IDownloadDetailsView viewDetailsView;

  private final UseCase getDownloadDetailsUseCase;
  private final DownloadModelDataMapper userModelDataMapper;

  @Inject
  public DownloadDetailsPresenter(@Named("userDetails") UseCase getDownloadDetailsUseCase, DownloadModelDataMapper userModelDataMapper) {
    this.getDownloadDetailsUseCase = getDownloadDetailsUseCase;
    this.userModelDataMapper = userModelDataMapper;
  }

  public void setView(@NonNull IDownloadDetailsView view) {
    this.viewDetailsView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getDownloadDetailsUseCase.unsubscribe();
    this.viewDetailsView = null;
  }

  /**
   * Initializes the presenter by start retrieving user details.
   */
  public void initialize() {
    this.loadDownloadDetails();
  }

  /**
   * Loads user details.
   */
  private void loadDownloadDetails() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getDownloadDetails();
  }

  private void showViewLoading() {
    this.viewDetailsView.showLoading();
  }

  private void hideViewLoading() {
    this.viewDetailsView.hideLoading();
  }

  private void showViewRetry() {
    this.viewDetailsView.showRetry();
  }

  private void hideViewRetry() {
    this.viewDetailsView.hideRetry();
  }

  private void showErrorMessage(IErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.viewDetailsView.context(), errorBundle.getException());
    this.viewDetailsView.showError(errorMessage);
  }

  private void showDownloadDetailsInView(Download user) {
    final DownloadModel userModel = this.userModelDataMapper.transform(user);
    this.viewDetailsView.renderDownload(userModel);
  }

  private void getDownloadDetails() {
    this.getDownloadDetailsUseCase.execute(new DownloadDetailsSubscriber());
  }

  @RxLogSubscriber
  private final class DownloadDetailsSubscriber extends DefaultSubscriber<Download> {

    @Override public void onCompleted() {
      DownloadDetailsPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      DownloadDetailsPresenter.this.hideViewLoading();
      DownloadDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      DownloadDetailsPresenter.this.showViewRetry();
    }

    @Override public void onNext(Download user) {
      DownloadDetailsPresenter.this.showDownloadDetailsInView(user);
    }
  }
}
