package legalFreedom.java.view.home;


import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import legalFreedom.java.model.data.dto.Book;
import legalFreedom.java.model.data.response.BooksResponse;
import legalFreedom.java.model.service.HomeService;
import legalFreedom.java.util.rx.RxSchedulersAbs;
import legalFreedom.java.util.rx.RxSchedulersTest;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HomePresenterTest {
    private HomeContract.Presenter presenter;
    private HomeContract.View view;
    private HomeService homeService;
    private RxSchedulersAbs rxSchedulersAbs;

    @Before
    public void before() {
        homeService = mock(HomeService.class);
        rxSchedulersAbs = new RxSchedulersTest();
        view = mock(HomeContract.View.class);
        presenter = new HomePresenter(homeService, rxSchedulersAbs);
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

        homeService.getBooks()
                .compose(rxSchedulersAbs.getComputationToMainTransformer())
                .subscribe(booksResponse -> {
                    Book book = booksResponse.getBooks().get(0);
                    assertEquals(idBook, book.getId());
                    assertEquals(nameBook, book.getName());
                    assertEquals(idBookString, book.getBookId());
                    log(booksResponse.getBooks().toString());
                });

        presenter.bindView(view);

        presenter.loadBooks();
        verify(view).showProgress();
        verify(view).hideProgress();
    }

    private void log(String message) {
        System.out.println(getClass().getSimpleName() + ": " + message);
    }

}