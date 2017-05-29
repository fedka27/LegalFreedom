package legalFreedom.java.view.home;

import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import legalFreedom.java.model.data.dto.Book;
import legalFreedom.java.model.data.response.BooksResponse;
import legalFreedom.java.model.data.response.CategoriesResponse;
import legalFreedom.java.model.service.CategoryService;
import legalFreedom.java.model.service.HomeService;
import legalFreedom.java.util.connection.ConnectionUtilAbs;
import legalFreedom.java.util.rx.RxSchedulersAbs;

public class HomePresenter implements HomeContract.Presenter {
    private HomeService homeService;
    private CategoryService categoryService;
    private ConnectionUtilAbs connectionUtil;
    private RxSchedulersAbs rxSchedulersAbs;
    private HomeContract.View view;
    private Gson gson;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public HomePresenter(HomeService homeService,
                         CategoryService categoryService,
                         ConnectionUtilAbs connectionUtil,
                         Gson gson,
                         RxSchedulersAbs rxSchedulersAbs) {
        this.homeService = homeService;
        this.categoryService = categoryService;
        this.connectionUtil = connectionUtil;
        this.gson = gson;
        this.rxSchedulersAbs = rxSchedulersAbs;
    }

    @Override
    public void bindView(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void loadBooks() {
        compositeDisposable.clear();
        Observable<BooksResponse> booksResponseObservable;
        if (connectionUtil.isOnline()) {
            booksResponseObservable = homeService.getBooks();
        } else {
            booksResponseObservable = homeService.getBooksOffline();
        }
        compositeDisposable.add(booksResponseObservable
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
        compositeDisposable.clear();
        Observable<CategoriesResponse> categoriesResponseObservable;
        if (connectionUtil.isOnline()) {
            categoriesResponseObservable = categoryService.getCategories(book.getBookId(),
                    book.getLang());
        } else {
            categoriesResponseObservable = categoryService.getCategoriesOffline(book.getBookId(),
                    book.getLang());
        }
        compositeDisposable.add(categoriesResponseObservable
                .compose(rxSchedulersAbs.getIOToMainTransformer())
                .doOnSubscribe(disposable -> view.showProgress(book.getName()))
                .subscribe(category -> {
                            view.hideProgress();
                            view.openCategoryActivity(book, category.getCategoryList());
                        },
                        throwable -> {
                            throwable.printStackTrace();
                            view.showError(throwable.getMessage());
                            view.hideProgress();
                        }));
    }


    @Override
    public void onStop() {
        compositeDisposable.clear();
    }
}
