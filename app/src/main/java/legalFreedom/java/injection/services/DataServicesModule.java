package legalFreedom.java.injection.services;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import legalFreedom.java.model.api.Api;
import legalFreedom.java.model.service.CategoryService;
import legalFreedom.java.model.service.DetailDocumentService;
import legalFreedom.java.model.service.HomeService;

@Module
@DataServicesScope
public class DataServicesModule {

    @Provides
    @DataServicesScope
    DetailDocumentService provideDetailDocumentService(Api api) {
        return new DetailDocumentService(api);
    }

    @Provides
    @DataServicesScope
    HomeService provideHomeService(Gson gson,
                                   Api api) {
        return new HomeService(api, gson);
    }

    @Provides
    @DataServicesScope
    CategoryService provideCategoryService(Gson gson,
                                           Api api) {
        return new CategoryService(api, gson);
    }
}
