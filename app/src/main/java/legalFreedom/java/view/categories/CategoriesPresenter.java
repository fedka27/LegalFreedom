package legalFreedom.java.view.categories;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import legalFreedom.java.model.data.dto.Book;
import legalFreedom.java.model.data.dto.Category;
import legalFreedom.java.model.service.CategoryService;
import legalFreedom.java.model.service.DetailDocumentService;
import legalFreedom.java.util.connection.ConnectionUtilAbs;
import legalFreedom.java.util.rx.RxSchedulersAbs;

public class CategoriesPresenter implements CategoriesContract.Presenter {
    private CategoryService categoryService;
    private CategoriesContract.View view;
    private ConnectionUtilAbs connectionUtilAbs;
    private DetailDocumentService detailDocumentService;
    private RxSchedulersAbs rxSchedulersAbs;
    private CompositeDisposable compositeDisposable;
    private Book book;

    public CategoriesPresenter(CategoryService categoryService,
                               ConnectionUtilAbs connectionUtilAbs,
                               DetailDocumentService detailDocumentService,
                               RxSchedulersAbs rxSchedulersAbs) {
        this.categoryService = categoryService;
        this.connectionUtilAbs = connectionUtilAbs;
        this.detailDocumentService = detailDocumentService;
        this.rxSchedulersAbs = rxSchedulersAbs;

        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void setBook(@NonNull Book book) {
        this.book = book;
    }

    @Override
    public void bindView(CategoriesContract.View view) {
        this.view = view;
    }

    @Override
    public void onCategoryPressed(Category category) {
        loadDocument(category);
    }

    private void loadDocument(Category category) {
        compositeDisposable.clear();
        Observable<String> documentTextObservable;
        if (connectionUtilAbs.isOnline()) {
            documentTextObservable = detailDocumentService.getDocumentHtml(book.getBookId(),
                    book.getLang(), category.getDocumentId());
        } else {
            documentTextObservable = detailDocumentService.getDocumentHtmlOffline(book.getBookId(),
                    book.getLang(), category.getDocumentId());
        }
        compositeDisposable.add(documentTextObservable
                .compose(rxSchedulersAbs.getIOToMainTransformer())
                .doOnSubscribe(disposable -> view.showProgress(category.getName()))
                .subscribe(textHtml -> {
                    view.hideProgress();
                    view.openDetailDocumentScreen(category.getName(), textHtml);
                }, throwable -> {
                    throwable.printStackTrace();
                    view.hideProgress();
                    view.showError(throwable.getMessage());
                }));
    }

    @Override
    public void search(String newText) {
        view.filterCategoriesBy(newText);
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
    }
}
