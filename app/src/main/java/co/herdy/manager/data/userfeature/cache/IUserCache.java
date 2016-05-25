package co.herdy.manager.data.userfeature.cache;

import co.herdy.manager.data.userfeature.payload.UserPayload;

import rx.Observable;

/**
 * An interface representing a user Cache.
 */
public interface IUserCache {
    /**
     * Gets an {@link rx.Observable} which will emit a {@link UserPayload}.
     *
     * @param userId The user id to retrieve data.
     */
    Observable<UserPayload> get(final int userId);

    /**
     * Puts and element into the cache.
     *
     * @param userPayload Element to insert in the cache.
     */
    void put(UserPayload userPayload);

    /**
     * Checks if an element (User) exists in the cache.
     *
     * @param userId The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final int userId);

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();
}
