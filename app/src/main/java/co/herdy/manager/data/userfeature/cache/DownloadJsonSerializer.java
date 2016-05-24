package co.herdy.manager.data.userfeature.cache;

import co.google.gson.Gson;
import co.herdy.manager.data.userfeature.payload.DownloadPayload;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class user as Serializer/Deserializer for user entities.
 */
@Singleton
public class DownloadJsonSerializer {

  private final Gson gson = new Gson();

  @Inject
  public DownloadJsonSerializer() {}

  /**
   * Serialize an object to Json.
   *
   * @param userPayload {@link DownloadPayload} to serialize.
   */
  public String serialize(DownloadPayload userPayload) {
    String jsonString = gson.toJson(userPayload, DownloadPayload.class);
    return jsonString;
  }

  /**
   * Deserialize a json representation of an object.
   *
   * @param jsonString A json string to deserialize.
   * @return {@link DownloadPayload}
   */
  public DownloadPayload deserialize(String jsonString) {
    DownloadPayload userPayload = gson.fromJson(jsonString, DownloadPayload.class);
    return userPayload;
  }
}
