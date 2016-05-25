package co.herdy.manager.domain.userfeature.interactor;

import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.domain.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link co.herdy.manager.domain.userfeature.model.User}.
 */
public class GetUserDetailsInteractor extends UseCase {

    private final String userKey;
    private final co.herdy.manager.domain.userfeature.repository.IUserRepository userRepository;

    @Inject
    public GetUserDetailsInteractor(String userKey, IUserRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userKey = userKey;
        this.userRepository = userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.userRepository.getItem(this.userKey);
    }
}
