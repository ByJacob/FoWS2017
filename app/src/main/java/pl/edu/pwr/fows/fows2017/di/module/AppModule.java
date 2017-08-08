package pl.edu.pwr.fows.fows2017.di.module;

import android.app.Application;
import android.content.SharedPreferences;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.edu.pwr.fows.fows2017.facebookPost.FacebookClient;
import pl.edu.pwr.fows.fows2017.gateway.FacebookPostGateway;
import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;
import pl.edu.pwr.fows.fows2017.menu.MenuClient;
import pl.edu.pwr.fows.fows2017.sharedPreferencesAPI.SharedPreferencesAPIClient;
import pl.edu.pwr.fows.fows2017.sharedPreferencesAPI.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.tools.SharedPreferencesAPI;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    MenuGateway getMenuGateway(){
        return new MenuClient();
    }

    @Provides
    @Singleton
    SharedPreferencesDataInterface getSharedPreferences(){
        return new SharedPreferencesAPI(application);
    }

    @Provides
    @Singleton
    @Named("LocalGateway")
    FacebookPostGateway getFacebookPostGatewaySharedPref(SharedPreferencesDataInterface sharedPreferences){
        return new SharedPreferencesAPIClient(sharedPreferences);
    }

    @Provides
    @Singleton
    @Named("NetworkGateway")
    FacebookPostGateway getFacebookPostsGateway(SharedPreferencesDataInterface sharedPreferences){
        return new FacebookClient(sharedPreferences);
    }

    @Provides
    @Singleton
    Application getApplication() {
        return application;
    }

    @Provides
    @Singleton
    @Named("SubscribeOnScheduler")
    public Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named("ObserveOnScheduler")
    public Scheduler provideAndroidMainThread() {
        return AndroidSchedulers.mainThread();
    }
}
