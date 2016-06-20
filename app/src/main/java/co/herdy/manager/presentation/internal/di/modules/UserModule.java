package co.herdy.manager.presentation.internal.di.modules;

import javax.inject.Named;

import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.domain.interactor.UseCase;
import co.herdy.manager.domain.userfeature.usecase.GetUserDetailsUseCase;
import co.herdy.manager.domain.userfeature.usecase.GetUserListUseCase;
import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import co.herdy.manager.presentation.internal.di.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class UserModule {

    private int userInt = 0;

    public UserModule() {
    }

    public UserModule(int userId) {
        this.userInt = userId;
    }

    @Provides
    @PerActivity
    @Named("userList")
    UseCase provideGetUserListUseCase(GetUserListUseCase getUserList) {
        return getUserList;
    }

    @Provides
    @PerActivity
    @Named("userDetails")
    UseCase provideGetUserDetailsUseCase(IUserRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        return new GetUserDetailsUseCase(userInt, userRepository, threadExecutor, postExecutionThread);
    }
}