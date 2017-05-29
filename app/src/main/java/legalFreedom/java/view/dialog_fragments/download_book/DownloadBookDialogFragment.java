package legalFreedom.java.view.dialog_fragments.download_book;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import legalFreedom.R;
import legalFreedom.java.view.base.BaseDialogFragment;

public class DownloadBookDialogFragment extends BaseDialogFragment implements DownloadBookContract.View {
    @Inject
    protected DownloadBookContract.Presenter presenter;

    public static DownloadBookDialogFragment newInstance() {
        return new DownloadBookDialogFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        ComponentProvider.getInstance().getPresentersComponent().inject(this);
        presenter.bindView(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_download_book, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
