package legalFreedom.java.injection;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import legalFreedom.java.injection.manager.ManagerComponent;
import legalFreedom.java.injection.manager.ManagerModule;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Context getContext();

    ManagerComponent plus(ManagerModule managerModule);
}
