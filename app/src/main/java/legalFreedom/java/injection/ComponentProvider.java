package legalFreedom.java.injection;

import android.app.Application;

public final class ComponentProvider {
    private static volatile ComponentProvider instance;

    private AppComponent appComponent;
    private ApiComponent apiComponent;
    private DataServiceComponent dataServiceComponent;
    private ManagerComponent managerComponent;

    public static ComponentProvider getInstance() {
        if (instance == null) {
            synchronized (ComponentProvider.class) {
                if (instance == null) {
                    instance = new ComponentProvider();
                }
            }
        }
        return instance;
    }

    public void init(Application application) {
        AppModule appModule = new AppModule(application);

        appComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .build();
        apiComponent = DaggerApiComponent.builder()
                .appModule(appModule)
                .build();
        dataServiceComponent = DaggerDataServiceComponent.builder()
                .appModule(appModule)
                .build();
        managerComponent = DaggerManagerComponent.builder()
                .appModule(appModule)
                .build();
    }

    public AppComponent getAppComponent() {
        checkInitialized(appComponent);
        return appComponent;
    }

    public ApiComponent getApiComponent() {
        checkInitialized(apiComponent);
        return apiComponent;
    }

    public DataServiceComponent getDataServiceComponent() {
        checkInitialized(dataServiceComponent);
        return dataServiceComponent;
    }

    public ManagerComponent getManagerComponent() {
        checkInitialized(managerComponent);
        return managerComponent;
    }

    private void checkInitialized(Object component) {
        if (component == null) {
            throw new ComponentProviderNotInitializedException();
        }
    }
}
