package legalFreedom.java.view.detail;

import legalFreedom.java.view.base.BasePresenter;
import legalFreedom.java.view.base.BaseView;

public interface DetailPageContract {

    interface View extends BaseView {

        void showProgressDialog();

        void hideProgressDialog();

        void showError(String message);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
