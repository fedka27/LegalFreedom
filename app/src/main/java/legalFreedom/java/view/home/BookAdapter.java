package legalFreedom.java.view.home;

import android.view.ViewGroup;

import java.util.List;

import legalFreedom.java.view.base.BaseRecyclerAdapter;
import legalFreedom.java.model.data.dto.Book;
import legalFreedom.java.view.base.RecyclerRow;


class BookAdapter extends BaseRecyclerAdapter {

    private OnBookListener onBookListener;

    BookAdapter(){
        recyclerRow.addRow(new RecyclerRow.Row<BookHolder, Book>() {
            @Override
            public boolean is(Object item) {
                return item instanceof Book;
            }

            @Override
            public BookHolder viewHolder(ViewGroup parent) {
                return new BookHolder(parent);
            }

            @Override
            public void bind(BookHolder bookHolder, Book item) {
                bookHolder.bind(item);

                bookHolder.setOnBookClickListener(v -> {
                    if (onBookListener != null){
                        onBookListener.onBookPressed(item);
                    }
                });
            }

            @Override
            public int typeRaw() {
                return 0;
            }
        });
    }

    void setOnBookListener(OnBookListener onBookListener) {
        this.onBookListener = onBookListener;
    }

    void setBooks(List<Book> bookList){
        itemList.clear();
        itemList.addAll(bookList);
        notifyDataSetChanged();
    }

    interface OnBookListener{
        void onBookPressed(Book book);
    }
}
