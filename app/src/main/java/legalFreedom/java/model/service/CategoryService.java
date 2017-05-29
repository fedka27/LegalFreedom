package legalFreedom.java.model.service;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;

import io.reactivex.Observable;
import legalFreedom.java.model.api.Api;
import legalFreedom.java.model.data.mapper.ApiResponseMapper;
import legalFreedom.java.model.data.response.CategoriesResponse;
import legalFreedom.java.util.LocalDataUtil;

public class CategoryService {
    private Api api;
    private Gson gson;

    public CategoryService(Api api, Gson gson) {
        this.api = api;
        this.gson = gson;
    }

    public io.reactivex.Observable<CategoriesResponse> getCategories(@NonNull String bookId,
                                                                     @NonNull String lang) {
        return api.getCategories(bookId, lang).map(new ApiResponseMapper<>());
    }

    public Observable<CategoriesResponse> getCategoriesOffline(String bookId, String lang) {
        return Observable.create(observableEmitter -> {
            BufferedReader bufferedReader = new BufferedReader(new FileReader
                    (LocalDataUtil.getCategories(bookId, lang)));
            CategoriesResponse response = gson
                    .fromJson(bufferedReader, CategoriesResponse.class);
            observableEmitter.onNext(response);
        });
    }
}
