package legalFreedom.java.injection;

import android.app.Application;

import legalFreedom.java.injection.api.ApiComponent;
import legalFreedom.java.injection.api.ApiModule;
import legalFreedom.java.injection.manager.ManagerComponent;
import legalFreedom.java.injection.manager.ManagerModule;
import legalFreedom.java.injection.presenters.PresentersComponent;
import legalFreedom.java.injection.presenters.PresentersModule;
import legalFreedom.java.injection.services.DataServiceComponent;
import legalFreedom.java.injection.services.DataServicesModule;

public final class ComponentProvider {
    private static volatile ComponentProvider instance;

    private AppComponent appComponent;
    private ManagerComponent managerComponent;
    private ApiComponent apiComponent;
    private DataServiceComponent dataServiceComponent;
    private PresentersComponent presentersComponent;

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

        managerComponent = appComponent.plus(new ManagerModule());
        apiComponent = managerComponent.plus(new ApiModule());
        dataServiceComponent = apiComponent.plus(new DataServicesModule());
        presentersComponent = dataServiceComponent.plus(new PresentersModule());

    }

    public AppComponent getAppComponent() {
        checkInitialized(appComponent);
        return appComponent;
    }

    public ApiComponent getApiComponent() {
        checkInitialized(apiComponent);
        return apiComponent;
    }

    public PresentersComponent getPresentersComponent() {
        checkInitialized(presentersComponent);
        return presentersComponent;
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
