package co.herdy.manager.data.userfeature.repository.datasource;

import android.util.Log;

import com.fernandocejas.frodo.annotation.RxLogObservable;

import java.util.List;

import co.herdy.manager.data.exception.NetworkConnectionException;
import co.herdy.manager.data.rest.ApiManager;
import co.herdy.manager.data.userfeature.cache.IUserCache;
import co.herdy.manager.data.userfeature.payload.UserPayload;
import co.herdy.manager.data.utility.NetworkUtility;
import co.herdy.manager.domain.userfeature.repository.datastore.IUserDataStore;
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
    public Observable<List<UserPayload>> getUserPayloadList() {
        Observable<List<UserPayload>> userListPayloadObservable = this.mApiManager.listUsers();
        return userListPayloadObservable.create(subscriber -> {
            if (NetworkUtility.isNetworkAvailable(mApiManager.getContext())) {
                List<UserPayload> responseUserPayload = null;
                try {
                    responseUserPayload = userListPayloadObservable.toBlocking().single();
                    subscriber.onNext(responseUserPayload);
                    subscriber.onCompleted();
                    Log.d(LOG_TAG, "OUR LIST CONTENTS " + responseUserPayload.toString());
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
    public Observable<UserPayload> getUserPayloadDetails(final int userId) {
        Observable<UserPayload> userDetailsPayloadObservable = this.mApiManager.getUserById(userId).doOnNext(saveToCacheAction);
        return userDetailsPayloadObservable.create(subscriber -> {
            UserPayload responseUserDetails = null;
            if (NetworkUtility.isNetworkAvailable(mApiManager.getContext())) {
                try {
                    responseUserDetails = userDetailsPayloadObservable.toBlocking().single();
                    subscriber.onNext(responseUserDetails);
                    subscriber.onCompleted();
                    Log.d(LOG_TAG, "OUR DETAILS CONTENTS " + responseUserDetails.toString());
                } catch (Exception e) {
                    subscriber.onError(new NetworkConnectionException(e.getCause()));
                }

            } else {
                subscriber.onError(new NetworkConnectionException());
            }
        });
    }

    @Override
    public Observable<String> authLoginUser(String userEmail, String userPassword) {
        Observable<String> userAuthTokenObservable = this.mApiManager.loginUser(userEmail, userPassword);
        return userAuthTokenObservable.create(subscriber -> {
            String authToken = null;
            if (NetworkUtility.isNetworkAvailable(mApiManager.getContext())) {
                try {
                    authToken = userAuthTokenObservable.toBlocking().single();
                    subscriber.onNext(authToken);
                    subscriber.onCompleted();
                    Log.d(LOG_TAG, "OUR LOGIN CONTENTS " + authToken.toString());
                } catch (Exception e) {
                    subscriber.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                subscriber.onError(new NetworkConnectionException());
            }
        });
    }

    @Override
    public Observable<UserPayload> authRegisterUser(UserPayload payload) {
        Observable<UserPayload> userRegisterPayloadObservable = this.mApiManager.registerUser(payload);
        return userRegisterPayloadObservable.create(subscriber -> {
            UserPayload userPayload = null;
            if (NetworkUtility.isNetworkAvailable(mApiManager.getContext())) {
                try {
                    userPayload = userRegisterPayloadObservable.toBlocking().single();
                    subscriber.onNext(userPayload);
                    subscriber.onCompleted();
                    Log.d(LOG_TAG, "OUR REGISTER CONTENTS " + userPayload.toString());
                } catch (Exception e) {
                    subscriber.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                subscriber.onError(new NetworkConnectionException());
            }
        });
    }
}
