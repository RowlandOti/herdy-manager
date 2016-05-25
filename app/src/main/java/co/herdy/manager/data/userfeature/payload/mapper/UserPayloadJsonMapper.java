package co.herdy.manager.data.userfeature.payload.mapper;

import co.herdy.manager.data.userfeature.payload.UserPayload;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Inject;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class UserPayloadJsonMapper {

    private final Gson gson;

    @Inject
    public UserPayloadJsonMapper() {
        this.gson = new Gson();
    }

    /**
     * Transform from valid json string to {@link UserPayload}.
     *
     * @param userJsonResponse A json representing a user profile.
     * @return {@link UserPayload}.
     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
     */
    public UserPayload transformUserEntity(String userJsonResponse) throws JsonSyntaxException {
        try {
            Type userEntityType = new TypeToken<UserPayload>() {}.getType();
            UserPayload userPayload = this.gson.fromJson(userJsonResponse, userEntityType);

            return userPayload;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }

    /**
     * Transform from valid json string to List of {@link UserPayload}.
     *
     * @param userListJsonResponse A json representing a collection of users.
     * @return List of {@link UserPayload}.
     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
     */
    public List<UserPayload> transformUserEntityCollection(String userListJsonResponse)
            throws JsonSyntaxException {

        List<UserPayload> userPayloadCollection;
        try {
            Type listOfUserEntityType = new TypeToken<List<UserPayload>>() {}.getType();
            userPayloadCollection = this.gson.fromJson(userListJsonResponse, listOfUserEntityType);

            return userPayloadCollection;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }
}
