package legalFreedom.java.injection;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, DataServiceModule.class})
public interface DataServiceComponent {

//    DataService getDataService();
}
