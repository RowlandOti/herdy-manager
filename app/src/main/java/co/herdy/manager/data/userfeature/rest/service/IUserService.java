package co.herdy.manager.data.userfeature.rest.service;

import java.util.List;

import co.herdy.manager.data.userfeature.payload.UserPayload;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface IUserService {

    // Constants for Users API End Points
    String USERS = "/";
    String USERS_ITEM = "/users/{key}";

    @GET(USERS)
    Observable<List<UserPayload>> listUsers();

    @POST(USERS)
    Observable<UserPayload> createUser(UserPayload payload);

    @GET(USERS_ITEM)
    Observable<UserPayload> getUserWithId(@Path("userId") int userId);
}
