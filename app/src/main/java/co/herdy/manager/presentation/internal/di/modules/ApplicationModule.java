package co.herdy.manager.presentation.internal.di.modules;

import android.content.Context;

import co.herdy.manager.data.userfeature.cache.UserCache;
import co.herdy.manager.data.userfeature.cache.IUserCache;
import co.herdy.manager.data.executor.JobThreadExecutor;
import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.presentation.ApplicationController;
import co.herdy.manager.presentation.UIThread;
import co.herdy.manager.presentation.userfeature.repository.UserDataRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final ApplicationController application;

    public ApplicationModule(ApplicationController application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    IThreadExecutor provideThreadExecutor(JobThreadExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    IPostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    IUserCache provideUserCache(UserCache userCache) {
        return userCache;
    }

    @Provides
    @Singleton
    IUserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }
}
