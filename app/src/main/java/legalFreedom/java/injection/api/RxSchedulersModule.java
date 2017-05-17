package legalFreedom.java.injection.api;

import dagger.Module;
import dagger.Provides;
import legalFreedom.java.util.rx.RxSchedulers;
import legalFreedom.java.util.rx.RxSchedulersAbs;

@Module
public class RxSchedulersModule {
    @Provides
    @ApiScope
    RxSchedulersAbs provideRxSchedulersAbs(){
        return new RxSchedulers();
    }
}
