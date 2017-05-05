package legalFreedom.java.view.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import legalFreedom.java.view.base.RecyclerRow.Row;

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    protected ArrayList<Object> itemList = new ArrayList<>();
    protected RecyclerRow recyclerRow = new RecyclerRow();

    @Override
    final public int getItemViewType(int position) {
        Row row = recyclerRow.getRow(itemList.get(position));
        return (row != null) ? row.typeRaw() : 0;
    }

    @Override
    final public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerRow.Row row = recyclerRow.getRow(viewType);
        return (row != null) ? row.viewHolder(parent) : null;
    }

    @Override
    final public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        Object item = itemList.get(position);
        RecyclerRow.Row row = recyclerRow.getRow(item);
        if (row != null) {
            row.bind(holder, item);
        }
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
