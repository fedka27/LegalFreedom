package legalFreedom.java.view.categories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import legalFreedom.R;
import legalFreedom.java.injection.ComponentProvider;
import legalFreedom.java.model.data.dto.Book;
import legalFreedom.java.model.data.dto.Category;
import legalFreedom.java.util.NotificationUtil;
import legalFreedom.java.view.base.BaseActivity;
import legalFreedom.java.view.detail.DetailPageActivity;
import legalFreedom.java.view.widgets.toolbars.SearchToolbar;

public class CategoriesActivity extends BaseActivity implements CategoriesContract.View {
    private static final String TAG = CategoriesActivity.class.getSimpleName();
    private static final String KEY_BOOK = TAG + "_BOOK_ID";
    private static final String KEY_CATEGORIES = TAG + "_CATEGORIES";
    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    SearchToolbar toolbar;
    private NotificationUtil notificationUtil;
    @Inject
    CategoriesContract.Presenter presenter;
    private CategoriesAdapter categoriesAdapter;

    public static void initViews(Context context, Book book, ArrayList<Category> categoryList) {
        Intent starter = new Intent(context, CategoriesActivity.class);
        starter.putExtra(KEY_BOOK, book)
                .putExtra(KEY_CATEGORIES, categoryList);
        context.startActivity(starter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentProvider.getInstance().getPresentersComponent().inject(this);
        presenter.bindView(this);
        setContentView(R.layout.activity_categories);
        notificationUtil = new NotificationUtil(this, recyclerView);
        initAdapter();
        initViews();
    }

    @SuppressWarnings("unchecked")
    private void initViews() {
        toolbar.setOnSearchListener(query -> presenter.search(query));

        Book book = (Book) getIntent().getSerializableExtra(KEY_BOOK);
        presenter.setBook(book);
        categoriesAdapter.setCategories((List<Category>) getIntent().getSerializableExtra(KEY_CATEGORIES));
    }

    private void initAdapter() {
        categoriesAdapter = new CategoriesAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(categoriesAdapter);
        categoriesAdapter.setOnItemClickListener(category -> presenter.onCategoryPressed(category));
    }

    @Override
    public void showProgress() {
        notificationUtil.showProgress();
    }

    @Override
    public void showProgress(String whatIsLoading) {
        notificationUtil.showProgress(whatIsLoading);
    }

    @Override
    public void hideProgress() {
        notificationUtil.hideProgress();
    }

    @Override
    public void showError(String message) {
        notificationUtil.showError(message);
    }

    @Override
    public void filterCategoriesBy(String newText) {
        categoriesAdapter.getFilter().filter(newText);
    }

    @Override
    public void openDetailDocumentScreen(String title, @NonNull String documentHtmlText) {
        DetailPageActivity.start(this, title, documentHtmlText);
    }
}
