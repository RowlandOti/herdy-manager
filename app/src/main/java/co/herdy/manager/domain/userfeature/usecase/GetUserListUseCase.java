package co.herdy.manager.domain.userfeature.usecase;

import javax.inject.Inject;

import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.domain.interactor.UseCase;
import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link User}.
 */
public class GetUserListUseCase extends UseCase {

    // Class log identifier
    public final static String LOG_TAG = GetUserListUseCase.class.getSimpleName();

    private final IUserRepository userRepository;

    @Inject
    public GetUserListUseCase(IUserRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.userRepository.getList();
    }
}
