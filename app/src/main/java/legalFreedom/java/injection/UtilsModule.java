package legalFreedom.java.injection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import legalFreedom.java.util.rx.RxSchedulers;
import legalFreedom.java.util.rx.RxSchedulersAbs;

@Module
public class UtilsModule {
    @Provides
    @Singleton
    public RxSchedulersAbs provideRxSchedulersAbs(){
        return new RxSchedulers();
    }
}
