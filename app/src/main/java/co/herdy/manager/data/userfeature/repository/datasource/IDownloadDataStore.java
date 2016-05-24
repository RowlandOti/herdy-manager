package co.herdy.manager.data.userfeature.repository.datasource;

import co.herdy.manager.data.userfeature.payload.DownloadPayload;

import java.util.List;
import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface IDownloadDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link DownloadPayload}.
   */
  Observable<List<String>> userPayloadList();

  /**
   * Get an {@link Observable} which will emit a {@link DownloadPayload} by its id.
   *
   * @param userKey The id to retrieve user data.
   */
  Observable<DownloadPayload> userPayloadDetails(final String userKey);
}
