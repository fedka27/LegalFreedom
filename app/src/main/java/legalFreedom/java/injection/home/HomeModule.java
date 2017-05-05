package legalFreedom.java.injection.home;

import dagger.Module;
import dagger.Provides;
import legalFreedom.java.model.api.Api;
import legalFreedom.java.model.service.HomeService;
import legalFreedom.java.util.rx.RxSchedulersAbs;
import legalFreedom.java.view.home.HomeContract;
import legalFreedom.java.view.home.HomePresenter;

@Module
public class HomeModule {

    @Provides
    @HomeScope
    public HomeService provideDataService(Api api){
        return new HomeService(api);
    }

    @Provides
    @HomeScope
    public HomeContract.Presenter provideHomePresenter(HomeService homeService, RxSchedulersAbs rxSchedulersAbs){
        return new HomePresenter(homeService, rxSchedulersAbs);
    }
}
