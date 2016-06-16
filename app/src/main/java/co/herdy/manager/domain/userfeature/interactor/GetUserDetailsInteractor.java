package co.herdy.manager.domain.userfeature.interactor;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.domain.interactor.UseCase;
import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link co.herdy.manager.domain.userfeature.model.User}.
 */
public class GetUserDetailsInteractor extends UseCase<Integer> {

    private final int userId;
    private final IUserRepository userRepository;

    @Inject
    public GetUserDetailsInteractor(int userId, IUserRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userId = userId;
        this.userRepository = userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable(@Nullable Integer[] params) {
        return this.userRepository.getItem(this.userId);
    }
}
