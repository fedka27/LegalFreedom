package legalFreedom.java.view.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import legalFreedom.R;
import legalFreedom.java.injection.ComponentProvider;
import legalFreedom.java.util.HtmlSpannableUtil;
import legalFreedom.java.view.base.BaseActivity;

public class DetailPageActivity extends BaseActivity
        implements DetailPageContract.View {
    private static final String TAG = DetailPageActivity.class.getSimpleName();
    private static final String KEY_BOOK_ID = TAG + "_BOOK_ID";
    private static final String KEY_LANG = TAG + "_LANG";
    private static final String KEY_DOCUMENT_ID = TAG + "_DOCUMENT_ID";
    @BindView(R.id.html_tv)
    TextView documentTextView;
    @Inject
    protected DetailPageContract.Presenter presenter;

    public static void start(Activity activity, String bookId, String lang, int documentId) {
        Intent intent = new Intent(activity, DetailPageActivity.class);
        intent.putExtra(KEY_BOOK_ID, bookId)
                .putExtra(KEY_LANG, lang)
                .putExtra(KEY_DOCUMENT_ID, documentId);
        activity.startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    private void initPresenter() {
        ComponentProvider.getInstance().getPresentersComponent().inject(this);
        presenter.bindView(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();

        setContentView(R.layout.activity_detail_page);

        start();
    }
    private void start(){
        String bookId = getIntent().getStringExtra(KEY_BOOK_ID);
        String lang = getIntent().getStringExtra(KEY_LANG);
        int docId = getIntent().getIntExtra(KEY_DOCUMENT_ID, 0);

        presenter.loadDocument(bookId, lang, docId);
    }

    @Override
    public void showProgressDialog() {
        showBaseProgress(documentTextView);
    }

    @Override
    public void hideProgressDialog() {
        hideBaseProgress();
    }

    @Override
    public void setDocument(String documentHtml) {
        documentTextView.setText(HtmlSpannableUtil.getString(documentHtml));
    }

    @Override
    public void showError(String message) {
        showBaseError(documentTextView, message);
    }
}
