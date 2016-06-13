package co.herdy.manager.domain.userfeature.interactor;

import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.domain.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link User}.
 */
public class GetUserListInteractor extends UseCase {

    // Class log identifier
    public final static String LOG_TAG = GetUserListInteractor.class.getSimpleName();

    private final IUserRepository userRepository;

    @Inject
    public GetUserListInteractor(IUserRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return this.userRepository.getList();
    }
}
