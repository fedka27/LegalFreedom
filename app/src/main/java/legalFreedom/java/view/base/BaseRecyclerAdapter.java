package legalFreedom.java.view.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import legalFreedom.java.view.base.RecyclerRow.Row;

public abstract class BaseRecyclerAdapter<MAIN_OBJECT extends Object> extends RecyclerView.Adapter<BaseRecyclerViewHolder>
        implements Filterable {
    private ArrayList<Object> itemListFiltered = new ArrayList<>();
    private ArrayList<Object> itemListNotFiltered = new ArrayList<>();
    protected RecyclerRow recyclerRow = new RecyclerRow();

    @Override
    final public int getItemViewType(int position) {
        Row row = recyclerRow.getRow(itemListFiltered.get(position));
        return (row != null) ? row.typeRaw() : 0;
    }

    @Override
    final public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerRow.Row row = recyclerRow.getRow(viewType);
        return (row != null) ? row.viewHolder(parent) : null;
    }

    @Override
    final public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        Object item = itemListFiltered.get(position);
        RecyclerRow.Row row = recyclerRow.getRow(item);
        if (row != null) {
            row.bind(holder, item);
        }
    }

    public void setItemList(List itemList) {
        this.itemListFiltered.clear();
        this.itemListNotFiltered.clear();
        this.itemListNotFiltered.addAll(itemList);
        this.itemListFiltered.addAll(itemList);
    }

    @Override
    public final Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                List<Object> results = new ArrayList<>();

                if (constraint.length() == 0) {
                    results = itemListNotFiltered;
                } else {
                    for (Object objects : itemListNotFiltered) {
                        if (isEqualsItem(objects, constraint.toString())) {
                            results.add(objects);
                        }
                    }
                }
                filterResults.values = results;
                return filterResults;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                itemListFiltered.clear();
                itemListFiltered.addAll((Collection<?>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public boolean isEqualsItem(Object objects, String query) {
        return true;
    }

    @Override
    public int getItemCount() {
        return itemListFiltered.size();
    }


}
