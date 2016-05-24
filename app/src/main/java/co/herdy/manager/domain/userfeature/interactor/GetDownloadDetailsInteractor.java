package co.herdy.manager.domain.userfeature.interactor;

import co.herdy.manager.domain.userfeature.repository.IDownloadRepository;
import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.domain.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link co.herdy.manager.domain.userfeature.model.Download}.
 */
public class GetDownloadDetailsInteractor extends UseCase {

    private final String userKey;
    private final co.herdy.manager.domain.userfeature.repository.IDownloadRepository userRepository;

    @Inject
    public GetDownloadDetailsInteractor(String userKey, IDownloadRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userKey = userKey;
        this.userRepository = userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.userRepository.getItem(this.userKey);
    }
}
