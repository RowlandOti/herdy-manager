package co.herdy.manager.domain.userfeature.repository.datastore;

import co.herdy.manager.data.userfeature.payload.UserPayload;

import java.util.List;
import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface IUserDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link UserPayload}.
   */
  Observable<List<UserPayload>> userPayloadList();

  /**
   * Get an {@link Observable} which will emit a {@link UserPayload} by its id.
   *
   * @param userId The id to retrieve user data.
   */
  Observable<UserPayload> userPayloadDetails(final int userId);
}
