package legalFreedom.java.injection.home;

import android.content.Context;

import com.google.gson.Gson;

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
    public HomeService provideDataService(Context context, Gson gson, Api api){
        return new HomeService(context, gson, api);
    }

    @Provides
    @HomeScope
    public HomeContract.Presenter provideHomePresenter(HomeService homeService, RxSchedulersAbs rxSchedulersAbs){
        return new HomePresenter(homeService, rxSchedulersAbs);
    }
}
