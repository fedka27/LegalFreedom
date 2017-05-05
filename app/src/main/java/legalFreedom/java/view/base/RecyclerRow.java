package legalFreedom.java.view.base;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import legalFreedom.java.util.L;

public class RecyclerRow {
    private List<Row> rowList = new ArrayList<>();

    public void addRow(Row row) {
        rowList.add(row);
    }

    public void removeRow(Row row) {
        rowList.remove(row);
    }

    public int size() {
        return rowList.size();
    }

    Row getRow(Object item) {
        for (Row row : rowList) {
            if (row.is(item)) {
                return row;
            }
        }
        L.e(getClass().getSimpleName(), "There is no such item type - " + item);
        return null;
    }

    Row getRow(int viewType) {
        for (Row row : rowList) {
            if (row.typeRaw() == viewType) {
                return row;
            }
        }
        L.e(getClass().getSimpleName(), "There is no such item type - " + viewType);
        return null;
    }

    public interface Row<HOLDER extends BaseRecyclerViewHolder, OBJECT> {

        boolean is(Object item);

        HOLDER viewHolder(ViewGroup parent);

        void bind(HOLDER holder, OBJECT item);

        int typeRaw();
    }
}