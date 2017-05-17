package legalFreedom.java.injection.services;

import android.content.Context;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import legalFreedom.java.model.api.Api;
import legalFreedom.java.model.service.CategoryService;
import legalFreedom.java.model.service.HomeService;

@Module
@DataServicesScope
public class DataServicesModule {

    @Provides
    @DataServicesScope
    HomeService provideHomeService(Context context,
                                   Gson gson,
                                   Api api) {
        return new HomeService(api, gson, context);
    }

    @Provides
    @DataServicesScope
    CategoryService provideCategoryService(Context context,
                                           Gson gson,
                                           Api api) {
        return new CategoryService(api, gson, context);
    }
}
