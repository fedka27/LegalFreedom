package legalFreedom.java.injection.api;

import com.google.gson.Gson;

import dagger.Subcomponent;
import legalFreedom.java.injection.services.DataServiceComponent;
import legalFreedom.java.injection.services.DataServicesModule;

@ApiScope
@Subcomponent(modules = {ApiModule.class, RxSchedulersModule.class})
public interface ApiComponent {

    DataServiceComponent plus(DataServicesModule dataServicesModule);

    Gson getGson();
}
