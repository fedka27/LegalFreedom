package legalFreedom.java.view.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import legalFreedom.R;
import legalFreedom.java.injection.ComponentProvider;
import legalFreedom.java.view.base.BaseActivity;

public class DetailPageActivity extends BaseActivity
        implements DetailPageContract.View {
    @Inject
    protected DetailPageContract.Presenter presenter;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, DetailPageActivity.class);
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

    }
}
