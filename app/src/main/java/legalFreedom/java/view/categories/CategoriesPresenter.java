package legalFreedom.java.view.categories;

import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import legalFreedom.java.model.service.CategoryService;
import legalFreedom.java.util.rx.RxSchedulersAbs;

public class CategoriesPresenter implements CategoriesContract.Presenter {
    private CategoryService categoryService;
    private RxSchedulersAbs rxSchedulersAbs;
    private CategoriesContract.View view;
    private CompositeDisposable compositeSubscription = new CompositeDisposable();
    private String bookId;

    public CategoriesPresenter(CategoryService categoryService, RxSchedulersAbs rxSchedulersAbs) {
        this.categoryService = categoryService;
        this.rxSchedulersAbs = rxSchedulersAbs;
    }

    @Override
    public void loadCategories(@NonNull String bookId) {
        this.bookId = bookId;
        compositeSubscription.add(categoryService.getCategories(bookId)
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
    public void onStop() {
        compositeSubscription.clear();
    }
}
