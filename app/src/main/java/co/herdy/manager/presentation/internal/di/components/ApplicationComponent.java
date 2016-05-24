/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in copliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package co.herdy.manager.presentation.internal.di.coponents;

import android.content.Context;

import co.herdy.manager.domain.userfeature.repository.IDownloadRepository;
import co.herdy.manager.domain.executor.IPostExecutionThread;
import co.herdy.manager.domain.executor.IThreadExecutor;
import co.herdy.manager.presentation.internal.di.modules.ApplicationModule;
import co.herdy.manager.presentation.view.activity.ABaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A coponent whose lifetime is the life of the application.
 */
@Singleton // Constraints this coponent to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(ABaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();

    IThreadExecutor threadExecutor();

    IPostExecutionThread postExecutionThread();
    
    IDownloadRepository userRepository();
}
