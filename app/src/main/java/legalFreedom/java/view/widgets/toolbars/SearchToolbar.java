package legalFreedom.java.view.widgets.toolbars;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.widget.ImageButton;

import butterknife.BindView;
import legalFreedom.R;
import legalFreedom.java.view.base.BaseToolbar;


public class SearchToolbar extends BaseToolbar {
    @BindView(R.id.back_image_button)
    ImageButton backImageButton;
    @BindView(R.id.search_view)
    SearchView searchView;
    private OnQueryTextListener onQueryTextListener;

    public SearchToolbar(@NonNull Context context) {
        super(context);
    }

    public SearchToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchToolbar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SearchToolbar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected int getLayout() {
        return R.layout.toolbar_search;
    }

    @Override
    protected void initViews() {
        super.initViews();
        searchView.setOnCloseListener(() -> {
            if (onQueryTextListener != null) {
                onQueryTextListener.onTextQuery("");
            }
            titleToolbarTv.setVisibility(VISIBLE);
            return false;
        });
        searchView.setOnSearchClickListener(v ->
                titleToolbarTv.setVisibility(GONE));
    }

    public void setOnSearchListener(OnQueryTextListener onSearchListener) {
        this.onQueryTextListener = onSearchListener;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                onSearchListener.onTextQuery(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                onSearchListener.onTextQuery(newText);
                return true;
            }
        });
    }

    public interface OnQueryTextListener {
        void onTextQuery(String query);
    }
}
