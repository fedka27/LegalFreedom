package legalFreedom.java.view.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import legalFreedom.R;

public abstract class BaseToolbar extends FrameLayout {
    @BindView(R.id.title_toolbar_tv)
    protected TextView titleToolbarTv;

    public BaseToolbar(@NonNull Context context) {
        super(context);
        init(null);
    }

    public BaseToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BaseToolbar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseToolbar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        View view = LayoutInflater.from(getContext()).inflate(getLayout(), this);

        ButterKnife.bind(this, view);

        if (attributeSet != null) {
            initAttrs(attributeSet);
        }
        initViews();
    }

    protected void initAttrs(AttributeSet attributeSet) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attributeSet,
                R.styleable.BaseToolbar, 0, 0);
        CharSequence titleSequence = typedArray.getText(R.styleable.BaseToolbar_titleToolbarText);
        setTitle(titleSequence);

        typedArray.recycle();

    }

    protected void initViews(){

    }

    public void setTitle(CharSequence titleSequence) {
        titleToolbarTv.setText(titleSequence);
    }

    @LayoutRes
    protected int getLayout() {
        return R.layout.toolbar_base;
    }
}
