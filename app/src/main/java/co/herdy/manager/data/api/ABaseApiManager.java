package co.herdy.manager.data.api;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import co.herdy.manager.data.authfeature.rest.service.IAuthService;
import co.herdy.manager.data.userfeature.rest.service.IUserService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
* Api manager extends this class to inherit base functionality
* */
public abstract class ABaseApiManager {

    // The class Log identifier
    private static final String LOG_TAG = ABaseApiManager.class.getSimpleName();
    protected Context mContext;
    private ApiApiEndPoint API_ENDPOINT = new ApiApiEndPoint();
    private IUserService mUserApi;
    private IAuthService mAuthApi;

    public ABaseApiManager() {
        createAuthApi();
        mUserApi = createApi(IUserService.class, API_ENDPOINT);
    }

    public void setupEndpoint(String instanceUrl) {
        API_ENDPOINT.updateInstanceUrl(instanceUrl);
    }

    /*
    * Generic class for creating service
    *
    * @Params IService service, IEndPoint endpoint
    * */
    private <T> T createApi(Class<T> clazz, ApiApiEndPoint endpoint) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).addInterceptor(logging).build();

        return new Retrofit.Builder()
                .baseUrl(endpoint.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build()
                .create(clazz);
    }

    /*
    *  Class to create the Auth Service
    * */
    private void createAuthApi() {
        mAuthApi = new Retrofit.Builder()
                .baseUrl(API_ENDPOINT.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(IAuthService.class);
    }

    protected IUserService getUsersApi() {
        return mUserApi;
    }

    protected IAuthService getAuthApi() {
        return mAuthApi;
    }

    public Context getContext() {
        return mContext;
    }
}
