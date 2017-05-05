package legalFreedom.java.injection.category;

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
    CategoryService provideDataService(Api api){
        return new CategoryService(api);
    }

    @Provides
    public CategoriesContract.Presenter providePresenter(CategoryService categoryService, RxSchedulersAbs rxSchedulersAbs){
        return new CategoriesPresenter(categoryService, rxSchedulersAbs);
    }
}
