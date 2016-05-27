package co.herdy.manager.data.authfeature.rest.service;


import co.herdy.manager.data.userfeature.payload.UserPayload;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IAuthService {

    // Constants for Users API End Points
    String AUTH = "/";
    String AUTH_LOGIN = "/";
    String AUTH_REGISTER = "/";

    @POST(AUTH_LOGIN)
    Observable<String> login(@Query("username") String username, @Query("password") String password);

    @POST(AUTH_REGISTER)
    Observable<UserPayload> register(@Body UserPayload payload);

}
