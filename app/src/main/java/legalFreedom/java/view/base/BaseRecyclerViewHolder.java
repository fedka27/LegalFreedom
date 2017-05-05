package legalFreedom.java.view.base;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


public abstract class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    public BaseRecyclerViewHolder(ViewGroup parent, @LayoutRes int layoutIdRes) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutIdRes, parent, false));
        ButterKnife.bind(this, itemView);
    }

    public View getView() {
        return itemView;
    }
}
