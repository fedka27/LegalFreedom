package legalFreedom.java.injection.presenters;

import dagger.Subcomponent;
import legalFreedom.java.view.categories.CategoriesActivity;
import legalFreedom.java.view.detail.DetailPageActivity;
import legalFreedom.java.view.home.HomeFragment;

@PresentersScope
@Subcomponent(modules = PresentersModule.class)
public interface PresentersComponent {
    void inject(HomeFragment homeFragment);

    void inject(CategoriesActivity categoriesActivity);

    void inject(DetailPageActivity detailPageActivity);
}
