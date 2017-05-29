package legalFreedom.java.util;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import legalFreedom.R;

import static android.support.design.widget.Snackbar.LENGTH_INDEFINITE;

public class NotificationUtil {
    private int DURATION_LONG = 3000;
    private int DURATION_INDEFINITE = LENGTH_INDEFINITE;
    @NonNull
    private Context context;
    @NonNull
    private View view;
    private Snackbar snackbar;

    public NotificationUtil(@NonNull Context context,
                            @NonNull View view) {
        this.context = context;
        this.view = view;
    }

    public void showError(String message) {
        showSnackBarNotification(message, DURATION_LONG, R.color.error);
    }

    public void showProgress() {
        showProgress(null);
    }

    public void showProgress(@Nullable String whatIsLoading) {
        String message = String.format("%s%s",
                context.getString(R.string.fragment_base_processing),
                whatIsLoading == null ? "" : "\n" + whatIsLoading);

        showSnackBarNotification(message, DURATION_INDEFINITE, R.color.progress);
    }

    private void showSnackBarNotification(String message, int duration, @ColorRes int color) {
        snackbar = Snackbar.make(view, message, duration);

        snackbar.getView()
                .setBackgroundColor(ContextCompat.getColor(context, color));

        snackbar.show();
    }

    public void hideProgress() {
        if (snackbar != null) {
            snackbar.dismiss();
        }
    }
}
