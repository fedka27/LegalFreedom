package legalFreedom.java.injection.home;

import dagger.Subcomponent;
import legalFreedom.java.view.home.HomeFragment;

@Subcomponent(modules = HomeModule.class)
@HomeScope
public interface HomeComponent {
    void inject(HomeFragment controller);
}
