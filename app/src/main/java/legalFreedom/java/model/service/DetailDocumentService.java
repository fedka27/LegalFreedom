package legalFreedom.java.model.service;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;

import io.reactivex.Observable;
import legalFreedom.java.model.api.Api;
import legalFreedom.java.model.data.mapper.ApiResponseMapper;
import legalFreedom.java.util.LocalDataUtil;
import okhttp3.ResponseBody;

public class DetailDocumentService {
    private Api api;

    public DetailDocumentService(Api api) {
        this.api = api;
    }

    public Observable<String> getDocumentHtml(@NonNull String bookId,
                                              @NonNull String lang,
                                              int documentId) {
        return api.getDocument(bookId, lang, documentId)
                .map(new ApiResponseMapper<>())
                .map(ResponseBody::string);

    }

    public Observable<String> getDocumentHtmlOffline(String bookId, String lang, int docId) {
        return io.reactivex.Observable.create(observableEmitter -> {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(LocalDataUtil
                    .getDocument(bookId, lang, docId)));
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((receiveString = bufferedReader.readLine()) != null) {
                stringBuilder.append(receiveString);
            }

            bufferedReader.close();
            observableEmitter.onNext(stringBuilder.toString());
        });
    }
}
