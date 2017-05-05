package legalFreedom.java.util;

import android.os.Build;
import android.support.annotation.StringRes;
import android.text.Html;
import android.text.Spanned;

import legalFreedom.java.injection.ComponentProvider;


public class HtmlSpannableUtil {

    public static Spanned getString(@StringRes int id) {
        return getSpanned(ComponentProvider.getInstance().getAppComponent()
                .getContext()
                .getString(id));
    }

    public static Spanned getString(String string) {
        return getSpanned(string);
    }

    private static Spanned getSpanned(String text) {
        if (text == null) {
            return null;
        }
        text = text.replace("\n", "<br>");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(text);
        }
    }
}
