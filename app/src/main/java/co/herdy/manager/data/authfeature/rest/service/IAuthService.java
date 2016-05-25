package co.herdy.manager.data.authfeature.rest.service;


import co.herdy.manager.data.userfeature.payload.UserPayload;
import retrofit2.Callback;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IAuthService {

    // Constants for Users API End Points
    String AUTH = "/";

    @POST(AUTH)
    void authenticate(@Query("username") String username, @Query("password") String password, Callback<UserPayload> userCallback);

}
