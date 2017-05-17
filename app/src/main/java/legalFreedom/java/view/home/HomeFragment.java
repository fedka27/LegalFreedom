package legalFreedom.java.view.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import legalFreedom.R;
import legalFreedom.java.injection.ComponentProvider;
import legalFreedom.java.model.data.dto.Book;
import legalFreedom.java.view.base.BaseFragment;
import legalFreedom.java.view.categories.CategoriesActivity;

public class HomeFragment extends BaseFragment implements HomeContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @Inject
    HomeContract.Presenter presenter;
    private BookAdapter adapter;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ComponentProvider.getInstance().getPresentersComponent().inject(this);
        presenter.bindView(this);
        return inflater.inflate(R.layout.ctrl_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.loadBooks();
    }

    private void initAdapter() {
        adapter = new BookAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnBookListener(book -> presenter.bookPressed(book));
    }

    @Override
    public void setBooks(List<Book> books) {
        adapter.setBooks(books);
    }

    @Override
    public void showProgress() {
        showBaseProgress();
    }

    @Override
    public void hideProgress() {
        hideBaseProgress();
    }

    @Override
    public void showError(String message) {
        showBaseError(message);
    }

    @Override
    public void openCategory(Book book) {
        CategoriesActivity.start(getContext(), book);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }
}
