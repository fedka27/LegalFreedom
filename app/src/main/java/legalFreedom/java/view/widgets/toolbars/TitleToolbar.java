package legalFreedom.java.view.widgets.toolbars;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.widget.ImageButton;

import butterknife.BindView;
import legalFreedom.R;
import legalFreedom.java.view.base.BaseToolbar;


public class TitleToolbar extends BaseToolbar {
    @BindView(R.id.nav_back_image_button)
    ImageButton navBackImageButton;

    public TitleToolbar(@NonNull Context context) {
        super(context);
    }

    public TitleToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleToolbar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleToolbar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initAttrs(AttributeSet attributeSet) {
        super.initAttrs(attributeSet);
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attributeSet,
                R.styleable.TitleToolbar, 0, 0);
        setVisibilityBackButton(typedArray.getBoolean(R.styleable.TitleToolbar_visibleBackButton, false));
        typedArray.recycle();
    }

    public void setVisibilityBackButton(boolean visible) {
        navBackImageButton.setVisibility(visible ? VISIBLE : INVISIBLE);
        requestLayout();
    }

    @Override
    protected int getLayout() {
        return R.layout.toolbar_title;
    }
}
