package legalFreedom.java.view.categories;

import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import legalFreedom.java.model.data.dto.Book;
import legalFreedom.java.model.data.dto.Category;
import legalFreedom.java.model.service.CategoryService;
import legalFreedom.java.util.rx.RxSchedulersAbs;

public class CategoriesPresenter implements CategoriesContract.Presenter {
    private CategoryService categoryService;
    private RxSchedulersAbs rxSchedulersAbs;
    private CategoriesContract.View view;
    private CompositeDisposable compositeSubscription = new CompositeDisposable();
    private Book book;

    public CategoriesPresenter(CategoryService categoryService, RxSchedulersAbs rxSchedulersAbs) {
        this.categoryService = categoryService;
        this.rxSchedulersAbs = rxSchedulersAbs;
    }

    @Override
    public void setBook(@NonNull Book book) {
        this.book = book;
    }

    @Override
    public void loadCategories() {
        assert book != null;
        compositeSubscription.add(categoryService.getCategories(book.getBookId(), book.getLang())
                .compose(rxSchedulersAbs.getIOToMainTransformer())
                .doOnSubscribe(disposable -> view.showProgress())
                .subscribe(category -> {
                            view.setCategoryList(category.getCat());
                            view.hideProgress();
                        },
                        throwable -> {
                            throwable.printStackTrace();
                            view.showError(throwable.getMessage());
                            view.hideProgress();
                        }));
    }

    @Override
    public void bindView(CategoriesContract.View view) {
        this.view = view;
    }

    @Override
    public void onCategoryPressed(Category category) {
        view.openDetailDocumentScreen(book.getBookId(), book.getLang(), category.getDocumentId());
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}
