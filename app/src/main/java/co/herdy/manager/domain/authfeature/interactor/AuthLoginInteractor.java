package co.herdy.manager.domain.authfeature.interactor;

import javax.inject.Inject;

import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.domain.interactor.UseCase;
import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import rx.Observable;

public class AuthLoginInteractor extends UseCase<String> {

    private final IUserRepository mUserRepository;

    @Inject
    public AuthLoginInteractor(IUserRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mUserRepository = userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(String... params) {
        return this.mUserRepository.authLoginUser(params[0], params[1]);
    }
}
