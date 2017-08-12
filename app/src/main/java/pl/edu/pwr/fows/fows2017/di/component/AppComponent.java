package pl.edu.pwr.fows.fows2017.di.component;

import javax.inject.Singleton;

import dagger.Component;
import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.application.FowsApplication;
import pl.edu.pwr.fows.fows2017.di.module.AppModule;
import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */
@SuppressWarnings("unused")
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    @SuppressWarnings("unused")
    void inject(FowsApplication fowsApplication);

    UseCaseFactory getUseCaseFactory();
}
