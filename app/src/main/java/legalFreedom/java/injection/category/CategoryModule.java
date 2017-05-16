package legalFreedom.java.injection.category;

import android.content.Context;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import legalFreedom.java.model.api.Api;
import legalFreedom.java.model.service.CategoryService;
import legalFreedom.java.util.rx.RxSchedulersAbs;
import legalFreedom.java.view.categories.CategoriesContract;
import legalFreedom.java.view.categories.CategoriesPresenter;

@Module
public class CategoryModule {
    @Provides
    CategoryService provideDataService(Api api, Gson gson, Context context) {
        return new CategoryService(api, gson, context);
    }

    @Provides
    public CategoriesContract.Presenter providePresenter(CategoryService categoryService, RxSchedulersAbs rxSchedulersAbs) {
        return new CategoriesPresenter(categoryService, rxSchedulersAbs);
    }
}
