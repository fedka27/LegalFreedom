package legalFreedom.java.model.api;

import legalFreedom.java.model.data.response.BooksResponse;
import legalFreedom.java.model.data.response.CategoriesResponse;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("pdd_{idBook}/lang_{lang}/categories.json")
    io.reactivex.Observable<Result<CategoriesResponse>> getCategories(@Path("idBook") String idBook,
                                                                      @Path("lang") String lang);

    @GET("books.json")
    io.reactivex.Observable<Result<BooksResponse>> getBooks();
}
