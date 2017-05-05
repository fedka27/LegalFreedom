package legalFreedom.java.view.home;

import io.reactivex.disposables.CompositeDisposable;
import legalFreedom.java.model.data.dto.Book;
import legalFreedom.java.model.service.HomeService;
import legalFreedom.java.util.rx.RxSchedulersAbs;

public class HomePresenter implements HomeContract.Presenter {
    private HomeService homeService;
    private RxSchedulersAbs rxSchedulersAbs;
    private HomeContract.View view;
    private CompositeDisposable compositeSubscription = new CompositeDisposable();

    public HomePresenter(HomeService homeService, RxSchedulersAbs rxSchedulersAbs) {
        this.homeService = homeService;
        this.rxSchedulersAbs = rxSchedulersAbs;
    }

    @Override
    public void bindView(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void loadBooks() {
        compositeSubscription.add(homeService.getBooks()
                .compose(rxSchedulersAbs.getIOToMainTransformer())
                .doOnSubscribe(disposable -> view.showProgress())
                .subscribe(booksResponse -> {
                    view.setBooks(booksResponse.getBooks());
                    view.hideProgress();
                }, throwable -> {
                    throwable.printStackTrace();
                    view.showError(throwable.getMessage());
                    view.hideProgress();
                }));

    }

    @Override
    public void bookPressed(Book book) {
        view.openCategory(book.getBookId());
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}
