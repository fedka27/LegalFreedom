package legalFreedom.java.view.categories;

import android.support.annotation.NonNull;

import legalFreedom.java.model.data.dto.Book;
import legalFreedom.java.model.data.dto.Category;
import legalFreedom.java.view.base.BasePresenter;
import legalFreedom.java.view.base.BaseView;

public interface CategoriesContract {

    interface View extends BaseView {

        void showProgress();

        void showError(String message);

        void hideProgress();

        void filterCategoriesBy(String newText);

        void openDetailDocumentScreen(String title, @NonNull String documentHtmlText);

        void showProgress(String whatIsLoading);
    }

    interface Presenter extends BasePresenter<View> {

        void setBook(@NonNull Book book);

        void onCategoryPressed(Category category);

        void search(String newText);
    }
}
