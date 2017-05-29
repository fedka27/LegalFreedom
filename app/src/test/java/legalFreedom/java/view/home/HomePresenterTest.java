package legalFreedom.java.view.home;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import legalFreedom.java.model.data.dto.Book;
import legalFreedom.java.model.data.response.BooksResponse;
import legalFreedom.java.model.service.CategoryService;
import legalFreedom.java.model.service.HomeService;
import legalFreedom.java.util.connection.ConnectionUtilTest;
import legalFreedom.java.util.rx.RxSchedulersAbs;
import legalFreedom.java.util.rx.RxSchedulersTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HomePresenterTest {
    private HomeContract.Presenter presenter;
    private HomeContract.View view;
    private CategoryService categoryService;
    private ConnectionUtilTest connectionUtilTest;
    private Gson gson;
    private HomeService homeService;
    private RxSchedulersAbs rxSchedulersAbs;

    @Before
    public void before() {
        homeService = mock(HomeService.class);
        categoryService = mock(CategoryService.class);
        connectionUtilTest = new ConnectionUtilTest();
        gson = new Gson();
        rxSchedulersAbs = new RxSchedulersTest();
        view = mock(HomeContract.View.class);
        presenter = new HomePresenter(homeService,
                categoryService,
                connectionUtilTest,
                gson,
                rxSchedulersAbs);
    }

    @Test
    public void loadBooks() throws Exception {
        long idBook = 2;
        String nameBook = "Test Name";
        String idBookString = "ru";
        List<Book> booksTest = Collections.singletonList(new Book(idBook, nameBook, idBookString));

        when(homeService.getBooks()).thenReturn(Observable.create(subscriber -> {
            BooksResponse response = new BooksResponse(booksTest);
            subscriber.onNext(response);
            subscriber.onComplete();
        }));
        when(homeService.getBooksOffline()).thenReturn(Observable.create(subscriber -> {
            BooksResponse response = new BooksResponse(booksTest);
            subscriber.onNext(response);
            subscriber.onComplete();
        }));

        presenter.bindView(view);
        presenter.loadBooks();
        verify(view).showProgress();
        verify(view).hideProgress();
    }

    private void log(String message) {
        System.out.println(getClass().getSimpleName() + ": " + message);
    }

}