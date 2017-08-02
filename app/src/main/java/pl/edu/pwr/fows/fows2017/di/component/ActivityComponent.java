package pl.edu.pwr.fows.fows2017.di.component;

import dagger.Component;
import pl.edu.pwr.fows.fows2017.activity.BaseActivity;
import pl.edu.pwr.fows.fows2017.di.module.ActivityModule;
import pl.edu.pwr.fows.fows2017.di.module.FragmentMainModule;
import pl.edu.pwr.fows.fows2017.di.scope.ActivityScope;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);

    FragmentMainComponent addModule(FragmentMainModule module);
}
