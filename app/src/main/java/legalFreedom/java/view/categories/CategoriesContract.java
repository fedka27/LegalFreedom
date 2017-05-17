package legalFreedom.java.view.categories;

import android.support.annotation.NonNull;

import java.util.List;

import legalFreedom.java.model.data.dto.Book;
import legalFreedom.java.model.data.dto.Category;
import legalFreedom.java.view.base.BasePresenter;
import legalFreedom.java.view.base.BaseView;

public interface CategoriesContract {

    interface View extends BaseView {

        void showProgress();

        void setCategoryList(List<Category> cat);

        void showError(String message);

        void hideProgress();

        void openDetailDocumentScreen(@NonNull String bookId,
                                      @NonNull String lang,
                                      int documentId);
    }

    interface Presenter extends BasePresenter<View> {

        void setBook(@NonNull Book book);

        void loadCategories();

        void onCategoryPressed(Category category);
    }
}
