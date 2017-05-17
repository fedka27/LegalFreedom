package legalFreedom.java.injection.manager;


import dagger.Subcomponent;
import legalFreedom.java.injection.api.ApiComponent;
import legalFreedom.java.injection.api.ApiModule;

@ManagerScope
@Subcomponent(modules = {ManagerModule.class})
public interface ManagerComponent {

    ApiComponent plus(ApiModule apiModule);
}
