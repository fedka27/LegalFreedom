package legalFreedom.java.injection.api;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import legalFreedom.java.util.connection.ConnectionUtil;
import legalFreedom.java.util.connection.ConnectionUtilAbs;
import legalFreedom.java.util.rx.RxSchedulers;
import legalFreedom.java.util.rx.RxSchedulersAbs;

@Module
public class UtilsModule {
    @Provides
    @ApiScope
    RxSchedulersAbs provideRxSchedulersAbs() {
        return new RxSchedulers();
    }

    @Provides
    @ApiScope
    ConnectionUtilAbs provideConnectionUtil(Context context) {
        return new ConnectionUtil(context);
    }
}
