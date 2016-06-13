package co.herdy.manager.domain.authfeature.interactor;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.domain.interactor.UseCase;
import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import rx.Observable;

public class AuthLoginInteractor extends UseCase {

    private final IUserRepository mUserRepository;
    private final String mEmail;
    private final String mPassword;

    @Inject
    protected AuthLoginInteractor(IUserRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mUserRepository = userRepository;
        this.mEmail = "email";
        this.mPassword = "password";
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.mUserRepository.authLoginUser(this.mEmail, this.mPassword);
    }
}
