package legalFreedom.java.view.home;

import java.util.ArrayList;
import java.util.List;

import legalFreedom.java.model.data.dto.Book;
import legalFreedom.java.model.data.dto.Category;
import legalFreedom.java.view.base.BasePresenter;
import legalFreedom.java.view.base.BaseView;

public interface HomeContract {

    interface View extends BaseView {

        void setBooks(List<Book> books);

        void openCategoryActivity(Book book, ArrayList<Category> categoryList);

        void showProgress();

        void showProgress(String whatIsLoading);

        void hideProgress();

        void showError(String message);
    }

    interface Presenter extends BasePresenter<View> {

        void loadBooks();

        void bookPressed(Book book);
    }
}
