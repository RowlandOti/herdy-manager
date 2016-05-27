package co.herdy.manager.data.userfeature.repository;

import android.util.Log;

import co.herdy.manager.data.userfeature.payload.UserPayload;
import co.herdy.manager.data.userfeature.payload.mapper.UserPayloadDataMapper;
import co.herdy.manager.domain.userfeature.repository.datastore.IUserDataStore;
import co.herdy.manager.data.userfeature.repository.datasource.UserDataStoreFactory;
import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import co.herdy.manager.domain.repository.IRepository;
import co.herdy.manager.domain.userfeature.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link IUserRepository} for retrieving user data.
 */
@Singleton
public class UserDataRepository implements IRepository, IUserRepository {

    // Class log identifier
    public final static String LOG_TAG = UserDataRepository.class.getSimpleName();

    private final UserDataStoreFactory userDataStoreFactory;
    private final UserPayloadDataMapper userPayloadDataMapper;

    /**
     * Constructs a {@link IUserRepository}.
     *
     * @param dataStoreFactory     A factory to construct different data source implementations.
     * @param userPayloadDataMapper {@link UserPayloadDataMapper}.
     */
    @Inject
    public UserDataRepository(UserDataStoreFactory dataStoreFactory, UserPayloadDataMapper userPayloadDataMapper) {
        this.userDataStoreFactory = dataStoreFactory;
        this.userPayloadDataMapper = userPayloadDataMapper;
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<List<UserPayload>> getList() {
        //we always get all users from the cloud
        final IUserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
        Log.d(LOG_TAG, "I WAS CALLED by getList");
        return userDataStore.userPayloadList().map(userPayload -> userPayload);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<User> getItem(int userId) {
        final IUserDataStore userDataStore = this.userDataStoreFactory.create(userId);
        Log.d(LOG_TAG, "I WAS CALLED by getItem");
        return userDataStore.userPayloadDetails(userId).map(userEntity -> this.userPayloadDataMapper.transform(userEntity));
    }
}
