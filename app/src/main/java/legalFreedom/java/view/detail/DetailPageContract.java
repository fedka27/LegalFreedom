package legalFreedom.java.view.detail;

import legalFreedom.java.view.base.BasePresenter;
import legalFreedom.java.view.base.BaseView;

public interface DetailPageContract {

    interface View extends BaseView {

        void showProgressDialog();

        void hideProgressDialog();

        void showError(String message);

        void setDocument(String documentHtml);
    }

    interface Presenter extends BasePresenter<View> {

        void loadDocument(String bookId, String lang, int docId);
    }
}
