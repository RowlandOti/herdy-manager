package co.herdy.manager.presentation.userfeature.view;

import co.herdy.manager.presentation.userfeature.model.DownloadModel;
import co.herdy.manager.presentation.view.ILoadDataView;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link DownloadModel}.
 */
public interface IDownloadListView extends ILoadDataView {
  /**
   * Render a user list in the UI.
   *
   * @param userModelCollection The collection of {@link DownloadModel} that will be shown.
   */
  void renderDownloadList(Collection<String> userModelCollection);

  /**
   * View a {@link DownloadModel} profile/details.
   *
   * @param userModel The user that will be shown.
   */
  void viewDownload(String userModel);
}
