package co.herdy.manager.domain.authfeature.interactor;

import javax.inject.Inject;

import co.herdy.manager.data.userfeature.payload.UserPayload;
import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.domain.interactor.UseCase;
import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import rx.Observable;

public class AuthRegisterInteractor extends UseCase {

    private final IUserRepository mUserRepository;
    private final UserPayload mUserPayload;

    @Inject
    public AuthRegisterInteractor(UserPayload userPayload,IUserRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mUserRepository = userRepository;
        this.mUserPayload = userPayload;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.mUserRepository.authRegisterUser(this.mUserPayload);
    }
}
