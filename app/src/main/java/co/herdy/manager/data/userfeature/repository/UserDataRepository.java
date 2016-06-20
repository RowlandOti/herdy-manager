package co.herdy.manager.data.userfeature.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.herdy.manager.data.userfeature.payload.UserPayload;
import co.herdy.manager.data.userfeature.payload.mapper.UserPayloadDataMapper;
import co.herdy.manager.data.userfeature.repository.datasource.UserDataStoreFactory;
import co.herdy.manager.domain.userfeature.model.User;
import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import co.herdy.manager.domain.userfeature.repository.datastore.IUserDataStore;
import rx.Observable;

/**
 * {@link IUserRepository} for retrieving user data.
 */
@Singleton
public class UserDataRepository implements IUserRepository {

    // Class log identifier
    public final static String LOG_TAG = UserDataRepository.class.getSimpleName();

    private final UserDataStoreFactory userDataStoreFactory;
    private final UserPayloadDataMapper userPayloadDataMapper;

    /**
     * Constructs a {@link IUserRepository}.
     *
     * @param dataStoreFactory      A factory to construct different data source implementations.
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
        // we always get all users from the cloud
        final IUserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
        return userDataStore.getUserPayloadList().map(userPayload -> userPayload);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<User> getItem(int userId) {
        final IUserDataStore userDataStore = this.userDataStoreFactory.create(userId);
        return userDataStore.getUserPayloadDetails(userId).map(userEntity -> this.userPayloadDataMapper.transform(userEntity));
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<String> authLoginUser(String email, String password) {
        final IUserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
        return userDataStore.authLoginUser(email, password).map(userToken -> userToken);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<UserPayload> authRegisterUser(User user) {
        UserPayload payload = userPayloadDataMapper.transform(user);
        final IUserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
        return userDataStore.authRegisterUser(payload).map(userPayload -> userPayload);
    }
}
