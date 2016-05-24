package co.herdy.manager.presentation.userfeature.mapper;

import co.herdy.manager.domain.userfeature.model.Download;
import co.herdy.manager.presentation.internal.di.PerActivity;
import co.herdy.manager.presentation.userfeature.model.DownloadModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Download} (in the domain layer) to {@link DownloadModel} in the
 * presentation layer.
 */
@PerActivity
public class DownloadModelDataMapper {

  @Inject
  public DownloadModelDataMapper() {}

  /**
   * Transform a {@link Download} into an {@link DownloadModel}.
   *
   * @param user Object to be transformed.
   * @return {@link DownloadModel}.
   */
  public DownloadModel transform(Download user) {
    if (user == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    DownloadModel userModel = new DownloadModel();
    userModel.setKey(user.getKey());
    userModel.setValue(user.getValue());

    return userModel;
  }

  /**
   * Transform a Collection of {@link Download} into a Collection of {@link DownloadModel}.
   *
   * @param usersCollection Objects to be transformed.
   * @return List of {@link DownloadModel}.
   */
  public Collection<DownloadModel> transform(Collection<Download> usersCollection) {
    Collection<DownloadModel> userModelsCollection;

    if (usersCollection != null && !usersCollection.isEmpty()) {
      userModelsCollection = new ArrayList<>();
      for (Download user : usersCollection) {
        userModelsCollection.add(transform(user));
      }
    } else {
      userModelsCollection = Collections.emptyList();
    }

    return userModelsCollection;
  }
}
