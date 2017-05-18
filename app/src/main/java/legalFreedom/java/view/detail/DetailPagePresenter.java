package legalFreedom.java.view.detail;

import io.reactivex.disposables.CompositeDisposable;
import legalFreedom.java.model.service.DetailDocumentService;
import legalFreedom.java.util.rx.RxSchedulersAbs;

public class DetailPagePresenter implements DetailPageContract.Presenter {
    private DetailPageContract.View view;
    private DetailDocumentService detailDocumentService;
    private RxSchedulersAbs rxSchedulersAbs;
    private CompositeDisposable compositeDisposable;

    public DetailPagePresenter(DetailDocumentService detailDocumentService, RxSchedulersAbs rxSchedulersAbs) {
        this.detailDocumentService = detailDocumentService;
        this.rxSchedulersAbs = rxSchedulersAbs;

        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void bindView(DetailPageContract.View view) {
        this.view = view;
    }

    @Override
    public void loadDocument(String bookId, String lang, int docId) {
        compositeDisposable.add(detailDocumentService.getDocumentHtml(bookId, lang, docId)
                .compose(rxSchedulersAbs.getIOToMainTransformer())
                .doOnSubscribe(disposable -> view.showProgressDialog())
                .subscribe(textHtml -> {
                    view.hideProgressDialog();
                    view.setDocument(textHtml);
                }, throwable -> {
                    throwable.printStackTrace();
                    view.hideProgressDialog();
                    view.showError(throwable.getMessage());
                }));
    }


    @Override
    public void onStop() {
        compositeDisposable.clear();
    }

}
