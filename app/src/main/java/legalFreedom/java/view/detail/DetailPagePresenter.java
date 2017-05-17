package legalFreedom.java.view.detail;

public class DetailPagePresenter implements DetailPageContract.Presenter {
    private DetailPageContract.View view;

    public DetailPagePresenter() {

    }

    @Override
    public void bindView(DetailPageContract.View view) {
        this.view = view;
    }


    @Override
    public void onStop() {

    }

}
