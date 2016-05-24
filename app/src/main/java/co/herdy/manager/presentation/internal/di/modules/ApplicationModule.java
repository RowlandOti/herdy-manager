package co.herdy.manager.presentation.internal.di.modules;

import android.content.Context;

import co.herdy.manager.data.userfeature.cache.DownloadCache;
import co.herdy.manager.data.userfeature.cache.IDownloadCache;
import co.herdy.manager.data.executor.JobThreadExecutor;
import co.herdy.manager.domain.userfeature.repository.IDownloadRepository;
import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.presentation.ApplicationController;
import co.herdy.manager.presentation.UIThread;
import co.herdy.manager.presentation.userfeature.repository.DownloadDataRepository;

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
    IDownloadCache provideDownloadCache(DownloadCache userCache) {
        return userCache;
    }

    @Provides
    @Singleton
    IDownloadRepository provideDownloadRepository(DownloadDataRepository userDataRepository) {
        return userDataRepository;
    }
}
