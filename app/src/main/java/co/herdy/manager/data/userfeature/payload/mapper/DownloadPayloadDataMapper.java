package co.herdy.manager.data.userfeature.payload.mapper;

import co.herdy.manager.data.userfeature.payload.DownloadPayload;
import co.herdy.manager.domain.userfeature.model.Download;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link DownloadPayload} (in the data layer) to {@link Download} in the
 * domain layer.
 */
@Singleton
public class DownloadPayloadDataMapper {

    @Inject
    public DownloadPayloadDataMapper() {
    }

    /**
     * Transform a {@link DownloadPayload} into an {@link Download}.
     *
     * @param userPayload Object to be transformed.
     * @return {@link Download} if valid {@link DownloadPayload} otherwise null.
     */
    public Download transform(DownloadPayload userPayload) {
        Download user = null;
        if (userPayload != null) {
            user = new Download();
            user.setKey(userPayload.getDownloadPayloadKey());
            user.setValue(userPayload.getValue());
        }
        return user;
    }

    /**
     * Transform a List of {@link DownloadPayload} into a Collection of {@link Download}.
     *
     * @param userPayloadCollection Object Collection to be transformed.
     * @return {@link Download} if valid {@link DownloadPayload} otherwise null.
     */
    public List<Download> transform(Collection<DownloadPayload> userPayloadCollection) {
        List<Download> userList = new ArrayList<>(20);
        Download user;
        for (DownloadPayload userPayload : userPayloadCollection) {
            user = transform(userPayload);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }
}

