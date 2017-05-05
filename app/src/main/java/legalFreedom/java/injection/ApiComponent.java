package legalFreedom.java.injection;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import legalFreedom.java.injection.category.CategoryComponent;
import legalFreedom.java.injection.home.HomeModule;
import legalFreedom.java.model.api.Api;
import legalFreedom.java.injection.category.CategoryModule;
import legalFreedom.java.injection.home.HomeComponent;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, UtilsModule.class})
public interface ApiComponent {

    Api getApiInterface();

    Gson getGson();

    HomeComponent plus(HomeModule homeModule);
    CategoryComponent plus(CategoryModule categoryModule);
}
