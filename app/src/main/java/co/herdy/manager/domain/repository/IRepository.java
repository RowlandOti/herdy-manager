package co.herdy.manager.domain.repository;


import java.util.List;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link Model} related data.
 */
public interface IRepository<T> {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link Model}.
     */
    Observable<List<T>> getList();

    /**
     * Get an {@link rx.Observable} which will emit a {@link Model}.
     *
     * @param itemId The user id used to retrieve user data.
     */
    Observable<T> getItem(final String itemId);
}

