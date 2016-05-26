package co.herdy.manager.presentation.internal.di.components;

import android.content.Context;

import co.herdy.manager.domain.userfeature.repository.IUserRepository;
import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.presentation.internal.di.modules.ApplicationModule;
import co.herdy.manager.presentation.view.activity.ABaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(ABaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();

    IThreadExecutor threadExecutor();

    IPostExecutionThread postExecutionThread();
    
    IUserRepository userRepository();
}
