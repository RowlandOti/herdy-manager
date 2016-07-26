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

import java.util.Date;
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
        a.setDate(new Date());
        a.setHatchno("L5");
        a.setSex("Male");
        a.setBreed("Checkered Giant");
        a.setStatus("Lactating");
        a.setWeaned(true);
        a.setLitter("SmallGs");
        a.save();

        Animal a1 = new Animal();
        a1.setName("Femi");
        a1.setDate(new Date());
        a1.setHatchno("L7");
        a1.setSex("Male");
        a1.setBreed("Carlifonian");
        a1.setStatus("Pregnant");
        a1.setWeaned(false);
        a1.setLitter("Brownies");
        a1.save();

        Animal a2 = new Animal();
        a2.setName("Meria");
        a2.setDate(new Date());
        a2.setHatchno("L9");
        a2.setSex("Male");
        a2.setBreed("American");
        a2.setStatus("Pregnant");
        a2.setWeaned(true);
        a2.setLitter("Sentinels");
        a2.save();
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
