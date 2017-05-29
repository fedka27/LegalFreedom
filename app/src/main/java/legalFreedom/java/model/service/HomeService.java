package legalFreedom.java.model.service;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;

import io.reactivex.Observable;
import legalFreedom.java.model.api.Api;
import legalFreedom.java.model.data.mapper.ApiResponseMapper;
import legalFreedom.java.model.data.response.BooksResponse;
import legalFreedom.java.util.LocalDataUtil;

public class HomeService {
    private Api api;
    private Gson gson;

    public HomeService(Api api, Gson gson) {
        this.api = api;
        this.gson = gson;
    }

    public Observable<BooksResponse> getBooks() {
        return api.getBooks().map(new ApiResponseMapper<>());
    }

    public Observable<BooksResponse> getBooksOffline() {
        return io.reactivex.Observable.create(observableEmitter -> {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(LocalDataUtil.getBooks()));
            BooksResponse response = gson.fromJson(bufferedReader, BooksResponse.class);
            observableEmitter.onNext(response);
        });
    }
}
