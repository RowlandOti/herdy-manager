package co.herdy.manager.presentation.internal.di.components;

import co.herdy.manager.presentation.authfeature.view.fragment.AuthLoginFragment;
import co.herdy.manager.presentation.authfeature.view.fragment.AuthRegisterFragment;
import co.herdy.manager.presentation.internal.di.PerActivity;
import co.herdy.manager.presentation.internal.di.modules.ActivityModule;
import co.herdy.manager.presentation.internal.di.modules.AuthModule;
import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, AuthModule.class})
public interface AuthComponent extends ActivityComponent {
    void inject(AuthLoginFragment authLoginFragment);

    void inject(AuthRegisterFragment userDetailsFragment);
}
