package co.herdy.manager.data.rest;

import android.content.Context;

import java.util.List;

import co.herdy.manager.data.userfeature.payload.UserPayload;
import rx.Observable;

public class ApiManager extends ABaseApiManager {

    // The class Log identifier
    private static final String LOG_TAG = ABaseApiManager.class.getSimpleName();

    public ApiManager(Context context) {
        mContext = context;
    }

    /**
     * Auth API
     */
    public Observable<String> loginUser(String username, String password) {
        return getAuthApi().login(username, password);
    }

    public Observable<UserPayload> registerUser(UserPayload payload) {
        return getAuthApi().register(payload);
    }

    /**
     * User API
     */
    public Observable<List<UserPayload>> listUsers() {
        return getUsersApi().listUsers();
    }

    public Observable<UserPayload> getUserById(int id) {
        return getUsersApi().getUserWithId(id);
    }
}
