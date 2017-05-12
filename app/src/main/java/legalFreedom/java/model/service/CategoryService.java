package legalFreedom.java.model.service;

import legalFreedom.java.model.api.Api;
import legalFreedom.java.model.data.mapper.ApiResponseMapper;
import legalFreedom.java.model.data.response.CategoriesResponse;

public class CategoryService {
    private Api api;

    public CategoryService(Api api) {
        this.api = api;
    }

    public io.reactivex.Observable<CategoriesResponse> getCategories(String bookId, String lang) {
        return api.getCategories(bookId, lang).map(new ApiResponseMapper<>());
    }
}
