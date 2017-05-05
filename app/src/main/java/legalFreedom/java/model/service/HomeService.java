package legalFreedom.java.model.service;

import io.reactivex.Observable;
import legalFreedom.java.model.api.Api;
import legalFreedom.java.model.data.mapper.ApiResponseMapper;
import legalFreedom.java.model.data.response.BooksResponse;

public class HomeService {
    private Api api;

    public HomeService(Api api) {
        this.api = api;
    }

    public Observable<BooksResponse> getBooks() {
        return api.getBooks().map(new ApiResponseMapper<>());
    }
}
