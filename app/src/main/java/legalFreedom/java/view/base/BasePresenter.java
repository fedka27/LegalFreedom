package legalFreedom.java.view.base;

public interface BasePresenter<VIEW> {
    void bindView(VIEW view);
    void onStop();
}
