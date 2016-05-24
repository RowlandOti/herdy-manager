package co.herdy.manager.presentation.internal.di.modules;

import co.herdy.manager.domain.userfeature.repository.IDownloadRepository;
import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.userfeature.interactor.GetDownloadDetailsInteractor;
import co.herdy.manager.domain.userfeature.interactor.GetDownloadListInteractor;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.domain.interactor.UseCase;
import co.herdy.manager.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class DownloadModule {

    private String userKey = null;

    public DownloadModule() {
    }

    public DownloadModule(String userKey) {
        this.userKey = userKey;
    }

    @Provides
    @PerActivity
    @Named("userList")
    UseCase provideGetDownloadListUseCase(GetDownloadListInteractor getDownloadList) {
        return getDownloadList;
    }

    @Provides
    @PerActivity
    @Named("userDetails")
    UseCase provideGetDownloadDetailsUseCase(IDownloadRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        return new GetDownloadDetailsInteractor(userKey, userRepository, threadExecutor, postExecutionThread);
    }
}