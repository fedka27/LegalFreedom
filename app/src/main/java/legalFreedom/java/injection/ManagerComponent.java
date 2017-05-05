package legalFreedom.java.injection;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ManagerModule.class})
public interface ManagerComponent {


}
