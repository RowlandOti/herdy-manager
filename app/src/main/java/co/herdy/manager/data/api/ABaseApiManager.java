package com.herdy.manager.data.api;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.herdy.manager.data.userfeature.rest.service.IDownloadService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public abstract class ABaseApiManager {

    // The class Log identifier
    private static final String LOG_TAG = ABaseApiManager.class.getSimpleName();

    private ApiApiEndPoint API_ENDPOINT = new ApiApiEndPoint();

    private IDownloadService mDownloadApi;
    protected Context mContext;

    public ABaseApiManager() {
        mDownloadApi = createApi(IDownloadService.class, API_ENDPOINT);
    }

    public void setupEndpoint(String instanceUrl) {
        API_ENDPOINT.updateInstanceUrl(instanceUrl);
    }


    private <T> T createApi(Class<T> clazz, ApiApiEndPoint endpoint) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).addInterceptor(logging).build();

        return new Retrofit.Builder()
                .baseUrl(endpoint.getUrl())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build()
                .create(clazz);
    }

    protected IDownloadService getDownloadsApi() {
        return mDownloadApi;
    }

    public Context getContext() {
        return mContext;
    }
}
