package legalFreedom.java.view.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import legalFreedom.R;
import legalFreedom.java.injection.ComponentProvider;
import legalFreedom.java.util.HtmlSpannableUtil;
import legalFreedom.java.util.NotificationUtil;
import legalFreedom.java.view.base.BaseActivity;
import legalFreedom.java.view.widgets.toolbars.TitleToolbar;

public class DetailPageActivity extends BaseActivity
        implements DetailPageContract.View {
    private static final String TAG = DetailPageActivity.class.getSimpleName();
    private static final String KEY_TITLE = TAG + "_TITLE";
    private static final String KEY_DOCUMENT = TAG + "_DOCUMENT_ID";
    @BindView(R.id.toolbar)
    TitleToolbar toolbar;
    @BindView(R.id.html_tv)
    TextView documentTextView;
    private NotificationUtil notificationUtil;
    @Inject
    protected DetailPageContract.Presenter presenter;

    public static void start(Activity activity, String title, String documentHtmlText) {
        Intent intent = new Intent(activity, DetailPageActivity.class);
        intent.putExtra(KEY_TITLE, title).putExtra(KEY_DOCUMENT, documentHtmlText);
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
        notificationUtil = new NotificationUtil(this, documentTextView);
        initViews();

    }

    private void initViews() {
        String textDocument = getIntent().getStringExtra(KEY_DOCUMENT);
        String title = getIntent().getStringExtra(KEY_TITLE);
        toolbar.setTitle(title);
        documentTextView.setMovementMethod(LinkMovementMethod.getInstance());
        documentTextView.setText(HtmlSpannableUtil.getString(textDocument));
    }


    @Override
    public void showProgressDialog() {
        notificationUtil.showProgress();
    }

    @Override
    public void hideProgressDialog() {
        notificationUtil.hideProgress();
    }

    @Override
    public void showError(String message) {
        notificationUtil.showError(message);
    }
}
