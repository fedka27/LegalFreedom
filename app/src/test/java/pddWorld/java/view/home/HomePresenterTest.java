package pddWorld.view.home;

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
        List<Book> booksTest = Collections.singletonList(new Book(0, "TestName", "BookId_rf"));

        when(homeService.getBooks()).thenReturn(Observable.create(subscriber -> {
            BooksResponse response = new BooksResponse(booksTest);
            subscriber.onNext(response);
            subscriber.onComplete();
        }));

        homeService.getBooks()
                .compose(rxSchedulersAbs.getComputationToMainTransformer())
                .subscribe(booksResponse -> {
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