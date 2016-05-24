package co.herdy.manager.presentation.userfeature.repository.datasource;

import android.util.Log;

import co.fernandocejas.frodo.annotation.RxLogObservable;
import co.herdy.manager.data.api.ApiManager;
import co.herdy.manager.data.userfeature.cache.IDownloadCache;
import co.herdy.manager.data.userfeature.payload.DownloadPayload;
import co.herdy.manager.data.userfeature.payload.DownloadPayloadCollection;
import co.herdy.manager.data.userfeature.repository.datasource.IDownloadDataStore;
import co.herdy.manager.data.exception.NetworkConnectionException;
import co.herdy.manager.data.utility.NetworkUtility;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * {@link IDownloadDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudDownloadDataStore implements IDownloadDataStore {

    // Class log identifier
    public final static String LOG_TAG = CloudDownloadDataStore.class.getSimpleName();

    //private final IRestApi restApi;
    private final ApiManager mApiManager;
    private final IDownloadCache userCache;
    private final Action1<DownloadPayload> saveToCacheAction = userEntity -> {
        if (userEntity != null) {
            CloudDownloadDataStore.this.userCache.put(userEntity);
        }
    };

    /**
     * Construct a {@link IDownloadDataStore} based on connections to the api (Cloud).
     *
     * @param apiManager    The {@link ApiManager} implementation to use.
     * @param userCache A {@link IDownloadCache} to cache data retrieved from the api.
     */
    public CloudDownloadDataStore(ApiManager apiManager, IDownloadCache userCache) {
        this.mApiManager = apiManager;
        this.userCache = userCache;
    }

    @RxLogObservable
    @Override
    public Observable<List<String>> userPayloadList() {
        Observable<DownloadPayloadCollection> userListPayloadObservable = this.mApiManager.listDownloads();
        return userListPayloadObservable.create(subscriber -> {
            if (NetworkUtility.isNetworkAvailable(mApiManager.getContext())) {
                List<String> responseDownloadPayload = null;
                try {
                    responseDownloadPayload = userListPayloadObservable.toBlocking().single().getResult();
                    subscriber.onNext(responseDownloadPayload);
                    subscriber.onCompleted();
                    Log.d(LOG_TAG, "OUR LIST CONTENTS " + userListPayloadObservable.toBlocking().single().toString());
                } catch (Exception e) {
                    subscriber.onError(new NetworkConnectionException(e.getCause()));
                    Log.d(LOG_TAG, "NETWORK UNKNOWNHOST EXCEPTION 1");
                }
            } else {
                subscriber.onError(new NetworkConnectionException());
                Log.d(LOG_TAG, "NETWORK INTERNET EXCEPTION");
            }
        });
    }

    @RxLogObservable
    @Override
    public Observable<DownloadPayload> userPayloadDetails(final String userKey) {
        Observable<DownloadPayload> userDetailsPayloadObservable = this.mApiManager.getDownloadById(userKey).doOnNext(saveToCacheAction);
        return userDetailsPayloadObservable.create(subscriber -> {
            DownloadPayload responseDownloadDetails = null;
            if (NetworkUtility.isNetworkAvailable(mApiManager.getContext())) {
                try {
                    responseDownloadDetails = userDetailsPayloadObservable.toBlocking().single();
                    subscriber.onNext(responseDownloadDetails);
                    subscriber.onCompleted();
                    Log.d(LOG_TAG, "OUR DETAILS CONTENTS " + userDetailsPayloadObservable.toBlocking().single().toString());
                } catch (Exception e) {
                    subscriber.onError(new NetworkConnectionException(e.getCause()));
                }

            } else {
                subscriber.onError(new NetworkConnectionException());
            }
        });
    }
}
