package legalFreedom.java.view.home;

import java.util.List;

import legalFreedom.java.view.base.BaseView;
import legalFreedom.java.model.data.dto.Book;
import legalFreedom.java.view.base.BasePresenter;

public interface HomeContract {

    interface View extends BaseView {

        void setBooks(List<Book> books);

        void openCategory(Book book);

        void showProgress();

        void hideProgress();

        void showError(String message);
    }

    interface Presenter extends BasePresenter<View> {

        void loadBooks();

        void bookPressed(Book book);
    }
}
