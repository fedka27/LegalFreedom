package legalFreedom.java.util;

import android.os.Environment;

public class LocalDataUtil {
    private static final String TAG = LocalDataUtil.class.getSimpleName();

    public static String getCategories(String bookId, String lang) {
        String localPath = Environment.getExternalStorageDirectory() + "/" + "pddSNG/" + "pdd_" + bookId + "_lang_" + lang + "/categories.json";
        L.e(TAG, " " + localPath);
        return localPath;
    }

    public static String getBooks() {
        String localPath = Environment.getExternalStoragePublicDirectory("pddSNG") + "/" + "books.json";
        L.e(TAG, " " + localPath);
        return localPath;
    }
}
