
package co.herdy.manager.presentation.internal.di.components;

import android.app.Activity;

import co.herdy.manager.presentation.internal.di.PerActivity;
import co.herdy.manager.presentation.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 * A base coponent upon which fragment's coponents may depend.
 * Activity-level coponents should extend this coponent.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  //Exposed to sub-graphs.
  Activity activity();
}
