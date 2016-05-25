package co.herdy.manager.data.userfeature.cache;

import co.herdy.manager.data.userfeature.payload.UserPayload;
import com.google.gson.Gson;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class user as Serializer/Deserializer for user entities.
 */
@Singleton
public class JsonSerializer {

  private final Gson gson = new Gson();

  @Inject
  public JsonSerializer() {}

  /**
   * Serialize an object to Json.
   *
   * @param userPayload {@link UserPayload} to serialize.
   */
  public String serialize(UserPayload userPayload) {
    String jsonString = gson.toJson(userPayload, UserPayload.class);
    return jsonString;
  }

  /**
   * Deserialize a json representation of an object.
   *
   * @param jsonString A json string to deserialize.
   * @return {@link UserPayload}
   */
  public UserPayload deserialize(String jsonString) {
    UserPayload userPayload = gson.fromJson(jsonString, UserPayload.class);
    return userPayload;
  }
}
