package co.herdy.manager.presentation.userfeature.repository.datasource;

import android.content.Context;

import co.herdy.manager.data.userfeature.cache.IDownloadCache;
import co.herdy.manager.data.userfeature.repository.datasource.IDownloadDataStore;
import co.herdy.manager.presentation.ApplicationController;
import co.herdy.manager.data.api.ApiManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link IDownloadDataStore}.
 */
@Singleton
public class DownloadDataStoreFactory {

    private final Context context;
    private final IDownloadCache userCache;

    @Inject
    public DownloadDataStoreFactory(Context context, IDownloadCache userCache) {
        if (context == null || userCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.userCache = userCache;
    }

    /**
     * Create {@link IDownloadDataStore} from a user id.
     */
    public IDownloadDataStore create(String userKey) {
        IDownloadDataStore userDataStore;

        if (!this.userCache.isExpired() && this.userCache.isCached(userKey)) {
            userDataStore = new DiskDownloadDataStore(this.userCache);
        } else {
            userDataStore = createCloudDataStore();
        }
        return userDataStore;
    }

    /**
     * Create {@link IDownloadDataStore} to retrieve data from the Cloud.
     */
    public IDownloadDataStore createCloudDataStore() {
        ApiManager apiManager = ApplicationController.apiManager;
        return new CloudDownloadDataStore(apiManager, this.userCache);
    }
}
