package co.herdy.manager.presentation;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import co.herdy.manager.BuildConfig;
import co.herdy.manager.data.api.ApiManager;
import co.herdy.manager.presentation.internal.di.coponents.ApplicationComponent;
import co.herdy.manager.presentation.internal.di.coponents.DaggerApplicationComponent;
import co.herdy.manager.presentation.internal.di.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

import java.util.HashMap;
import java.util.Map;

/**
 * Android Main Application
 */
public class ApplicationController extends Application {

    private ApplicationComponent appComponent;

    public static final Map<Integer, Typeface> typefaceManager = new HashMap<>();
    public static ApiManager apiManager;
    private static ApplicationController appControllerInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetection();

        appControllerInstance = this;
        apiManager = new ApiManager(this);
    }

    private void initializeInjector() {
        this.appComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    public ApplicationComponent getAppComponent() {
        return this.appComponent;
    }

    public static Context getContext() {
        return appControllerInstance;
    }

    public static ApplicationController getAppControllerInstance() {
        return appControllerInstance;
    }
}
