package legalFreedom.java.view.categories;

import android.view.ViewGroup;

import java.util.List;

import legalFreedom.java.model.data.dto.Category;
import legalFreedom.java.view.base.BaseRecyclerAdapter;
import legalFreedom.java.view.base.RecyclerRow;


class CategoriesAdapter extends BaseRecyclerAdapter {

    CategoriesAdapter(){
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
            }

            @Override
            public int typeRaw() {
                return 0;
            }
        });
    }

    void setCategories(List<Category> categoryList){
        itemList.addAll(categoryList);
        notifyDataSetChanged();
    }
}
