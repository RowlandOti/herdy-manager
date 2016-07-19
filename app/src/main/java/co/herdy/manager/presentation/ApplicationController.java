package co.herdy.manager.presentation;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import co.herdy.manager.BuildConfig;
import co.herdy.manager.data.rest.ApiManager;
import co.herdy.manager.domain.dashboardfeature.model.Animal;
import co.herdy.manager.presentation.internal.di.components.ApplicationComponent;
import co.herdy.manager.presentation.internal.di.components.DaggerApplicationComponent;
import co.herdy.manager.presentation.internal.di.modules.ApplicationModule;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
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
        //this.initializeLeakDetection();
        this.initializeActiveAndroid();
        this.initializeFakeData();

        appControllerInstance = this;
        apiManager = new ApiManager(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
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

    private void initializeActiveAndroid() {
        Configuration.Builder cfg = new Configuration.Builder(this);
        cfg.addModelClass(Animal.class);
        ActiveAndroid.initialize(cfg.create());
    }

    private void initializeFakeData() {
        // For Test
        Animal a = new Animal();
        a.setName("Gogi");
        a.setDate("12/07/2012");
        a.setHatchno("L5");
        a.save();
        Animal a1 = new Animal();
        a1.setName("Femi");
        a1.setDate("12/07/2012");
        a1.setHatchno("L7");
        a1.save();
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
