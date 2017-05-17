package legalFreedom.java.model.service;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.reactivex.Observable;
import legalFreedom.java.model.api.Api;
import legalFreedom.java.model.data.mapper.ApiResponseMapper;
import legalFreedom.java.util.ConnectionUtil;
import legalFreedom.java.util.LocalDataUtil;
import okhttp3.ResponseBody;

public class DetailDocumentService {
    private Context context;
    private Api api;
    private Gson gson;

    public DetailDocumentService(Context context, Api api, Gson gson) {
        this.context = context;
        this.api = api;
        this.gson = gson;
    }

    public Observable<String> getDocumentHtml(@NonNull String bookId,
                                              @NonNull String lang,
                                              int documentId) {
        if (ConnectionUtil.isOnline(context)) {
            return api.getDocument(bookId, lang, documentId)
                    .map(new ApiResponseMapper<>())
                    .map(ResponseBody::string);
        } else {
            return io.reactivex.Observable.create(observableEmitter -> {
                InputStream inputStream = new FileInputStream(new File(LocalDataUtil.getDocument(bookId, lang, documentId)));
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                observableEmitter.onNext(stringBuilder.toString());
            });
        }
    }
}
