package legalFreedom.java;

import android.support.multidex.MultiDex;

import io.paperdb.Paper;
import legalFreedom.java.injection.ComponentProvider;

public class App extends android.support.multidex.MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ComponentProvider.getInstance().init(this);
        Paper.init(this);
        MultiDex.install(this);
    }
}
