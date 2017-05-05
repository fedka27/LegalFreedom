package legalFreedom.java.injection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import legalFreedom.java.model.api.Api;
import legalFreedom.java.model.service.DataService;

@Singleton
@Module
public class DataServiceModule {

    @Singleton
    @Provides
    public DataService provideDataService(Api api){
        return new DataService(api);
    }
}
