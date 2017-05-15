package legalFreedom.java.view.home;

import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import legalFreedom.R;
import legalFreedom.java.model.data.dto.Book;
import legalFreedom.java.view.base.BaseRecyclerViewHolder;

class BookHolder extends BaseRecyclerViewHolder {
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.lang_tv)
    TextView langTv;
    @BindView(R.id.book_card_view)
    CardView cardView;

    BookHolder(ViewGroup parent) {
        super(parent, R.layout.cell_home_book);
    }

    void bind(Book book) {
        titleTv.setText(book.getName());
        langTv.setText(book.getLang());
    }

    void setOnBookClickListener(View.OnClickListener onBookClickListener) {
        cardView.setOnClickListener(onBookClickListener);
    }

}
