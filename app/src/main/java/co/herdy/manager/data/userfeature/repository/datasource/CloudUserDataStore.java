package co.herdy.manager.data.userfeature.repository.datasource;

import android.util.Log;

import java.util.List;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import co.herdy.manager.data.rest.ApiManager;
import co.herdy.manager.data.exception.NetworkConnectionException;
import co.herdy.manager.data.userfeature.cache.IUserCache;
import co.herdy.manager.data.userfeature.payload.UserPayload;
import co.herdy.manager.data.utility.NetworkUtility;
import rx.Observable;
import rx.functions.Action1;

/**
 * {@link IUserDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudUserDataStore implements IUserDataStore {

    // Class log identifier
    public final static String LOG_TAG = CloudUserDataStore.class.getSimpleName();

    //private final IRestApi restApi;
    private final ApiManager mApiManager;
    private final IUserCache userCache;
    private final Action1<UserPayload> saveToCacheAction = userEntity -> {
        if (userEntity != null) {
            CloudUserDataStore.this.userCache.put(userEntity);
        }
    };

    /**
     * Construct a {@link IUserDataStore} based on connections to the api (Cloud).
     *
     * @param apiManager The {@link ApiManager} implementation to use.
     * @param userCache  A {@link IUserCache} to cache data retrieved from the api.
     */
    public CloudUserDataStore(ApiManager apiManager, IUserCache userCache) {
        this.mApiManager = apiManager;
        this.userCache = userCache;
    }

    @RxLogObservable
    @Override
    public Observable<List<UserPayload>> userPayloadList() {
        Observable<List<UserPayload>> userListPayloadObservable = this.mApiManager.listUsers();
        return userListPayloadObservable.create(subscriber -> {
            if (NetworkUtility.isNetworkAvailable(mApiManager.getContext())) {
                List<UserPayload> responseUserPayload = null;
                try {
                    responseUserPayload = userListPayloadObservable.toBlocking().single();
                    subscriber.onNext(responseUserPayload);
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
    public Observable<UserPayload> userPayloadDetails(final int userId) {
        Observable<UserPayload> userDetailsPayloadObservable = this.mApiManager.getUserById(userId).doOnNext(saveToCacheAction);
        return userDetailsPayloadObservable.create(subscriber -> {
            UserPayload responseUserDetails = null;
            if (NetworkUtility.isNetworkAvailable(mApiManager.getContext())) {
                try {
                    responseUserDetails = userDetailsPayloadObservable.toBlocking().single();
                    subscriber.onNext(responseUserDetails);
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
