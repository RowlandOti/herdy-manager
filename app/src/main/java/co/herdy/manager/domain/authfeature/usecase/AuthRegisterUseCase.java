package co.herdy.manager.domain.authfeature.usecase;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.domain.interactor.UseCase;
import co.herdy.manager.domain.userfeature.model.User;
import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import rx.Observable;

public class AuthRegisterUseCase extends UseCase {

    private final IUserRepository mUserRepository;
    private User mUser;

    @Inject
    public AuthRegisterUseCase(IUserRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mUserRepository = userRepository;
    }

    /**
     * Initializes the interactor with the username and password to use for the authentication.
     *
     * @param username - username for the user
     * @param password - password for the user.
     */
    public void init(@NonNull String username, @NonNull String email, @NonNull String password) {
        if (username == null || email == null || password == null) {
            throw new IllegalArgumentException("init(username,password) not called, or called with null argument.");
        }
        this.mUser = new User();
        this.mUser.setEmail(email);
        this.mUser.setUsername(username);
        this.mUser.setPassword(password);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.mUserRepository.authRegisterUser(mUser);
    }
}
