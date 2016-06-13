package co.herdy.manager.domain.userfeature.repository.datastore;

import java.util.List;

import co.herdy.manager.data.userfeature.payload.UserPayload;
import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface IUserDataStore {
    /**
     * Get an {@link Observable} which will emit a List of {@link UserPayload}.
     */
    Observable<List<UserPayload>> getUserPayloadList();

    /**
     * Get an {@link Observable} which will emit a {@link UserPayload} by its id.
     *
     * @param userId The id to retrieve user data.
     */
    Observable<UserPayload> getUserPayloadDetails(final int userId);

    /**
     * Get an {@link Observable} which will emit a {@link UserPayload} by its id.
     *
     * @param userEmail    The user email.
     * @param userPassword The user password.
     */
    Observable<String> authLoginUser(String userEmail, String userPassword);

    /**
     * Get an {@link Observable} which will emit a {@link UserPayload} by its id.
     *
     * @param userPayload The user payload.
     */
    Observable<UserPayload> authRegisterUser(UserPayload userPayload);
}
