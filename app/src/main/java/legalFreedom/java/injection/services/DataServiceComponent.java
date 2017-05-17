package legalFreedom.java.injection.services;

import dagger.Subcomponent;
import legalFreedom.java.injection.presenters.PresentersComponent;
import legalFreedom.java.injection.presenters.PresentersModule;

@Subcomponent(modules = DataServicesModule.class)
@DataServicesScope
public interface DataServiceComponent {
    PresentersComponent plus(PresentersModule presentersModule);
}
