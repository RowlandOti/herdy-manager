package co.herdy.manager.domain.authfeature.usecase;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.domain.interactor.UseCase;
import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import rx.Observable;

public class AuthLoginUseCase extends UseCase {

    private final IUserRepository mUserRepository;
    private String mEmail;
    private String mPassword;

    @Inject
    public AuthLoginUseCase(IUserRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mUserRepository = userRepository;
    }

    /**
     * Initializes the interactor with the email and password to use for the authentication.
     *
     * @param email-   email for the user
     * @param password - password for the user.
     */
    public void init(@NonNull String email, @NonNull String password) {
        if (email == null || password == null) {
            throw new IllegalArgumentException("init(email,password) not called, or called with null argument.");
        }
        this.mEmail = email;
        this.mPassword = password;
    }


    @Override
    protected Observable buildUseCaseObservable() {
        return this.mUserRepository.authLoginUser(mEmail, mPassword);
    }
}
