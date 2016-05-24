package co.herdy.manager.presentation.userfeature.repository.datasource;

import co.herdy.manager.data.userfeature.repository.datasource.IDownloadDataStore;
import co.herdy.manager.data.userfeature.cache.IDownloadCache;
import co.herdy.manager.data.userfeature.payload.DownloadPayload;

import java.util.List;

import rx.Observable;

/**
 * {@link IDownloadDataStore} implementation based on file system data store.
 */
public class DiskDownloadDataStore implements IDownloadDataStore {

    private final IDownloadCache userCache;

    /**
     * Construct a {@link IDownloadDataStore} based file system data store.
     *
     * @param userCache A {@link IDownloadCache} to cache data retrieved from the api.
     */
    public DiskDownloadDataStore(IDownloadCache userCache) {
        this.userCache = userCache;
    }

    public Observable<List<String>> userPayloadList() {
        //TODO: implement simple cache for storing/retrieving collections of users.
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<DownloadPayload> userPayloadDetails(final String userKey) {
        return this.userCache.get(userKey);
    }


}
