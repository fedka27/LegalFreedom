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
    @BindView(R.id.book_card_view)
    CardView cardView;

    BookHolder(ViewGroup parent) {
        super(parent, R.layout.cell_home_book);
    }

    void bind(Book category) {
        titleTv.setText(category.getName());
    }

    void setOnBookClickListener(View.OnClickListener onBookClickListener) {
        cardView.setOnClickListener(onBookClickListener);
    }

}
