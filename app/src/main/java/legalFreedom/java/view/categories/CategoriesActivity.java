package legalFreedom.java.view.categories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import legalFreedom.R;
import legalFreedom.java.injection.ComponentProvider;
import legalFreedom.java.model.data.dto.Book;
import legalFreedom.java.model.data.dto.Category;
import legalFreedom.java.view.base.BaseActivity;

public class CategoriesActivity extends BaseActivity implements CategoriesContract.View {
    private static final String TAG = CategoriesActivity.class.getSimpleName();
    private static final String KEY_BOOK = TAG + "_BOOK_ID";
    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;
    @Inject
    CategoriesContract.Presenter presenter;
    private CategoriesAdapter categoriesAdapter;

    public static void start(Context context, Book book) {
        Intent starter = new Intent(context, CategoriesActivity.class);
        starter.putExtra(KEY_BOOK, book);
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
        setContentView(R.layout.ctrl_categories);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Book book = (Book) getIntent().getSerializableExtra(KEY_BOOK);
        initAdapter();

        presenter.setBook(book);
        presenter.loadCategories();
    }

    private void initAdapter() {
        categoriesAdapter = new CategoriesAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(categoriesAdapter);
    }

    @Override
    public void setCategoryList(List<Category> cat) {
        categoriesAdapter.setCategories(cat);
    }

    @Override
    public void showProgress() {
        showBaseProgress(recyclerView);
    }

    @Override
    public void hideProgress() {
        hideBaseProgress();
    }

    @Override
    public void showError(String message) {
        showBaseError(recyclerView, message);
    }
}
