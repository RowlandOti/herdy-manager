package co.herdy.manager.data.userfeature.payload.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.herdy.manager.data.userfeature.payload.UserPayload;
import co.herdy.manager.domain.userfeature.model.User;

/**
 * Mapper class used to transform {@link UserPayload} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class UserPayloadDataMapper {

    @Inject
    public UserPayloadDataMapper() {
    }

    /**
     * Transform a {@link UserPayload} into an {@link User}.
     *
     * @param userPayload Object to be transformed.
     * @return {@link User} if valid {@link UserPayload} otherwise null.
     */
    public User transform(UserPayload userPayload) {
        User user = null;
        if (userPayload != null) {
            user = new User();
            user.setFullname(userPayload.getFullname());
            user.setUsername(userPayload.getUsername());
            user.setEmail(userPayload.getEmail());
        }
        return user;
    }

    /**
     * Transform a List of {@link UserPayload} into a Collection of {@link User}.
     *
     * @param userPayloadCollection Object Collection to be transformed.
     * @return {@link User} if valid {@link UserPayload} otherwise null.
     */
    public List<User> transform(Collection<UserPayload> userPayloadCollection) {
        List<User> userList = new ArrayList<>(20);
        User user;
        for (UserPayload userPayload : userPayloadCollection) {
            user = transform(userPayload);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }

    /**
     * Transform a {@link UserPayload} into an {@link User}.
     *
     * @param user Object to be transformed.
     * @return {@link User} if valid {@link UserPayload} otherwise null.
     */
    public UserPayload transform(User user) {
        UserPayload userPayload = null;
        if (user != null) {
            userPayload = new UserPayload();
            userPayload.setUsername(user.getUsername());
            userPayload.setPassword(user.getPassword());
            userPayload.setEmail(user.getEmail());
        }
        return userPayload;
    }

}

