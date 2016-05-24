package co.herdy.manager.presentation.userfeature.repository;

import android.util.Log;

import co.herdy.manager.data.userfeature.payload.mapper.DownloadPayloadDataMapper;
import co.herdy.manager.data.userfeature.repository.datasource.IDownloadDataStore;
import co.herdy.manager.domain.userfeature.repository.IDownloadRepository;
import co.herdy.manager.domain.repository.IRepository;
import co.herdy.manager.domain.userfeature.model.Download;
import co.herdy.manager.presentation.userfeature.repository.datasource.DownloadDataStoreFactory;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link IDownloadRepository} for retrieving user data.
 */
@Singleton
public class DownloadDataRepository implements IRepository, IDownloadRepository {

    // Class log identifier
    public final static String LOG_TAG = DownloadDataRepository.class.getSimpleName();

    private final DownloadDataStoreFactory userDataStoreFactory;
    private final DownloadPayloadDataMapper userPayloadDataMapper;

    /**
     * Constructs a {@link IDownloadRepository}.
     *
     * @param dataStoreFactory     A factory to construct different data source implementations.
     * @param userPayloadDataMapper {@link DownloadPayloadDataMapper}.
     */
    @Inject
    public DownloadDataRepository(DownloadDataStoreFactory dataStoreFactory, DownloadPayloadDataMapper userPayloadDataMapper) {
        this.userDataStoreFactory = dataStoreFactory;
        this.userPayloadDataMapper = userPayloadDataMapper;
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<List<String>> getList() {
        //we always get all users from the cloud
        final IDownloadDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
        Log.d(LOG_TAG, "I WAS CALLED by getList");
        return userDataStore.userPayloadList().map(userPayload -> userPayload);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<Download> getItem(String userKey) {
        final IDownloadDataStore userDataStore = this.userDataStoreFactory.create(userKey);
        Log.d(LOG_TAG, "I WAS CALLED by getItem");
        return userDataStore.userPayloadDetails(userKey).map(userEntity -> this.userPayloadDataMapper.transform(userEntity));
    }
}
