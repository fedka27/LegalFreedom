package legalFreedom.java.view.detail;

public class DetailPagePresenter implements DetailPageContract.Presenter {
    private DetailPageContract.View view;


    @Override
    public void bindView(DetailPageContract.View view) {
        this.view = view;
    }


    @Override
    public void onStop() {
    }

}
