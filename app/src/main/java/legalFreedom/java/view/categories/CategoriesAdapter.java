package legalFreedom.java.view.categories;

import android.support.annotation.Nullable;
import android.view.ViewGroup;

import java.util.List;

import legalFreedom.java.model.data.dto.Category;
import legalFreedom.java.view.base.BaseRecyclerAdapter;
import legalFreedom.java.view.base.RecyclerRow;


class CategoriesAdapter extends BaseRecyclerAdapter {
    @Nullable
    private OnCategoryClickListener onCategoryClickListener;

    CategoriesAdapter() {
        recyclerRow.addRow(new RecyclerRow.Row<CategoryHolder, Category>() {
            @Override
            public boolean is(Object item) {
                return item instanceof Category;
            }

            @Override
            public CategoryHolder viewHolder(ViewGroup parent) {
                return new CategoryHolder(parent);
            }

            @Override
            public void bind(CategoryHolder categoryHolder, Category item) {
                categoryHolder.bind(item);
                categoryHolder.getView().setOnClickListener(v -> {
                    if (onCategoryClickListener != null) {
                        onCategoryClickListener.onClick(item);
                    }
                });
            }

            @Override
            public int typeRaw() {
                return 0;
            }
        });
    }

    void setCategories(List<Category> categoryList) {
        setItemList(categoryList);
        notifyDataSetChanged();
    }

    @Override
    public boolean isEqualsItem(Object objects, String query) {
        if (objects instanceof Category) {
            if (((Category) objects).getName().toUpperCase().contains(query.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    void setOnItemClickListener(OnCategoryClickListener onCategoryClickListener) {
        this.onCategoryClickListener = onCategoryClickListener;
    }

    interface OnCategoryClickListener {
        void onClick(Category category);
    }
}
