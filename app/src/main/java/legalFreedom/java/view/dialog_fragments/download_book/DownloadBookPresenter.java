package legalFreedom.java.view.dialog_fragments.download_book;

public class DownloadBookPresenter implements DownloadBookContract.Presenter {
    private DownloadBookContract.View view;

    public DownloadBookPresenter() {

    }

    @Override
    public void bindView(DownloadBookContract.View view) {
        this.view = view;
    }

    @Override
    public void onStop() {

    }

}
