package legalFreedom.java.injection.category;

import dagger.Subcomponent;
import legalFreedom.java.view.categories.CategoriesActivity;

@Subcomponent(modules = {CategoryModule.class})
public interface CategoryComponent {
    void inject(CategoriesActivity controller);
}
