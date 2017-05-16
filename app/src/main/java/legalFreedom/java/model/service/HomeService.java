package legalFreedom.java.model.service;

import android.content.Context;
import android.os.Environment;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;

import io.reactivex.Observable;
import legalFreedom.java.model.api.Api;
import legalFreedom.java.model.data.mapper.ApiResponseMapper;
import legalFreedom.java.model.data.response.BooksResponse;
import legalFreedom.java.util.ConnectionUtil;
import legalFreedom.java.util.L;
import legalFreedom.java.util.LocalDataUtil;

public class HomeService {
    private Context context;
    private Api api;
    private Gson gson;

    public HomeService(Context context, Gson gson, Api api) {
        this.context = context;
        this.api = api;
        this.gson = gson;
    }

    public Observable<BooksResponse> getBooks() {
        if (ConnectionUtil.isOnline(context)) {
            return api.getBooks().map(new ApiResponseMapper<>());
        } else {
            return io.reactivex.Observable.create(observableEmitter -> {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(LocalDataUtil.getBooks()));
                BooksResponse response = gson.fromJson(bufferedReader, BooksResponse.class);
                observableEmitter.onNext(response);
            });
        }
    }
}
