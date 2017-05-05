package legalFreedom.java.view.categories;

import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import legalFreedom.R;
import legalFreedom.java.model.data.dto.Category;
import legalFreedom.java.view.base.BaseRecyclerViewHolder;

class CategoryHolder extends BaseRecyclerViewHolder {
    @BindView(R.id.title_tv)
    TextView titleTv;

    public CategoryHolder(ViewGroup parent) {
        super(parent, R.layout.cell_category);
    }

    public void bind(Category category) {
        titleTv.setText(category.getName());
    }
}
