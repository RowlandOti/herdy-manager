package co.herdy.manager.data.userfeature.repository.datasource;

import co.herdy.manager.data.userfeature.repository.datasource.IUserDataStore;
import co.herdy.manager.data.userfeature.cache.IUserCache;
import co.herdy.manager.data.userfeature.payload.UserPayload;

import java.util.List;

import rx.Observable;

/**
 * {@link IUserDataStore} implementation based on file system data store.
 */
public class DiskUserDataStore implements IUserDataStore {

    private final IUserCache userCache;

    /**
     * Construct a {@link IUserDataStore} based file system data store.
     *
     * @param userCache A {@link IUserCache} to cache data retrieved from the api.
     */
    public DiskUserDataStore(IUserCache userCache) {
        this.userCache = userCache;
    }

    public Observable<List<UserPayload>> userPayloadList() {
        //TODO: implement simple cache for storing/retrieving collections of users.
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<UserPayload> userPayloadDetails(final int userKey) {
        return this.userCache.get(userKey);
    }


}
