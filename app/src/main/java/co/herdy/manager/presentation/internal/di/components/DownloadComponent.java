package co.herdy.manager.presentation.internal.di.coponents;

import co.herdy.manager.presentation.userfeature.view.fragment.DownloadDetailsFragment;
import co.herdy.manager.presentation.internal.di.PerActivity;
import co.herdy.manager.presentation.internal.di.modules.ActivityModule;
import co.herdy.manager.presentation.internal.di.modules.DownloadModule;
import co.herdy.manager.presentation.userfeature.view.fragment.DownloadListFragment;

import dagger.Component;

/**
 * A scope {@link PerActivity} coponent.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, DownloadModule.class})
public interface DownloadComponent extends ActivityComponent {
  void inject(DownloadListFragment userListFragment);
  void inject(DownloadDetailsFragment userDetailsFragment);
}
