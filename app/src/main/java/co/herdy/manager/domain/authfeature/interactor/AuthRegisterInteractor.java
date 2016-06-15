package co.herdy.manager.domain.authfeature.interactor;

import javax.inject.Inject;

import co.herdy.manager.data.userfeature.payload.UserPayload;
import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.domain.interactor.UseCase;
import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import rx.Observable;

public class AuthRegisterInteractor extends UseCase<UserPayload> {

    private final IUserRepository mUserRepository;

    @Inject
    public AuthRegisterInteractor(IUserRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mUserRepository = userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(UserPayload... userPayload) {
        return this.mUserRepository.authRegisterUser(userPayload[0]);
    }
}
