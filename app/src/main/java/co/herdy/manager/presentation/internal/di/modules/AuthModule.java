package co.herdy.manager.presentation.internal.di.modules;

import javax.inject.Named;

import co.herdy.manager.data.userfeature.payload.UserPayload;
import co.herdy.manager.domain.authfeature.interactor.AuthLoginInteractor;
import co.herdy.manager.domain.authfeature.interactor.AuthRegisterInteractor;
import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.domain.interactor.UseCase;
import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import co.herdy.manager.presentation.internal.di.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class AuthModule {

    private UserPayload mUserPayload;

    public AuthModule() {
    }

    public AuthModule(UserPayload userPayload) {
        this.mUserPayload = userPayload;
    }

    @Provides
    @PerActivity
    @Named("authLoginUser")
    UseCase provideAuthLoginUseCase(AuthLoginInteractor authLogin) {
        return authLogin;
    }

    @Provides
    @PerActivity
    @Named("authRegisterUser")
    UseCase provideAuthRegisterUseCase(IUserRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        return new AuthRegisterInteractor(mUserPayload, userRepository, threadExecutor, postExecutionThread);
    }
}