package legalFreedom.java.injection.presenters;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import legalFreedom.java.model.service.CategoryService;
import legalFreedom.java.model.service.DetailDocumentService;
import legalFreedom.java.model.service.HomeService;
import legalFreedom.java.util.connection.ConnectionUtilAbs;
import legalFreedom.java.util.rx.RxSchedulersAbs;
import legalFreedom.java.view.categories.CategoriesContract;
import legalFreedom.java.view.categories.CategoriesPresenter;
import legalFreedom.java.view.detail.DetailPageContract;
import legalFreedom.java.view.detail.DetailPagePresenter;
import legalFreedom.java.view.home.HomeContract;
import legalFreedom.java.view.home.HomePresenter;

@Module
@PresentersScope
public class PresentersModule {

    @Provides
    @PresentersScope
    DetailPageContract.Presenter provideDetailsPresenter(DetailDocumentService detailDocumentService,
                                                         ConnectionUtilAbs connectionUtilAbs,
                                                         RxSchedulersAbs rxSchedulersAbs) {
        return new DetailPagePresenter();
    }

    @Provides
    @PresentersScope
    CategoriesContract.Presenter provideCategoriesPresenter(CategoryService categoryService,
                                                            ConnectionUtilAbs connectionUtilAbs,
                                                            DetailDocumentService detailDocumentService,
                                                            RxSchedulersAbs rxSchedulersAbs) {
        return new CategoriesPresenter(categoryService,
                connectionUtilAbs,
                detailDocumentService,
                rxSchedulersAbs);
    }

    @Provides
    @PresentersScope
    HomeContract.Presenter provideHomePresenter(HomeService homeService,
                                                CategoryService categoryService,
                                                ConnectionUtilAbs connectionUtil,
                                                Gson gson,
                                                RxSchedulersAbs rxSchedulersAbs) {
        return new HomePresenter(homeService,
                categoryService,
                connectionUtil,
                gson,
                rxSchedulersAbs);
    }
}
