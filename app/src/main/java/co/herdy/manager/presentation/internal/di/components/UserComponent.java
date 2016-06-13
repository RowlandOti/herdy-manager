package co.herdy.manager.presentation.internal.di.components;

import co.herdy.manager.presentation.internal.di.PerActivity;
import co.herdy.manager.presentation.internal.di.modules.ActivityModule;
import co.herdy.manager.presentation.internal.di.modules.UserModule;
import co.herdy.manager.presentation.userfeature.view.fragment.UserDetailsFragment;
import co.herdy.manager.presentation.userfeature.view.fragment.UserListFragment;
import dagger.Component;

/**
 * A scope {@link PerActivity} coponent.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {
    void inject(UserListFragment userListFragment);

    void inject(UserDetailsFragment userDetailsFragment);
}
