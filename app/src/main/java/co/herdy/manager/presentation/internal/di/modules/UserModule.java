package co.herdy.manager.presentation.internal.di.modules;

import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.userfeature.interactor.GetUserDetailsInteractor;
import co.herdy.manager.domain.userfeature.interactor.GetUserListInteractor;
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
public class UserModule {

    private String userKey = null;

    public UserModule() {
    }

    public UserModule(String userKey) {
        this.userKey = userKey;
    }

    @Provides
    @PerActivity
    @Named("userList")
    UseCase provideGetUserListUseCase(GetUserListInteractor getUserList) {
        return getUserList;
    }

    @Provides
    @PerActivity
    @Named("userDetails")
    UseCase provideGetUserDetailsUseCase(IUserRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        return new GetUserDetailsInteractor(userKey, userRepository, threadExecutor, postExecutionThread);
    }
}