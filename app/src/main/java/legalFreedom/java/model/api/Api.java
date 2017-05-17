package legalFreedom.java.model.api;

import io.reactivex.Observable;
import legalFreedom.java.model.data.response.BooksResponse;
import legalFreedom.java.model.data.response.CategoriesResponse;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("books.json")
    Observable<Result<BooksResponse>> getBooks();

    @GET("pdd_{idBook}_lang_{lang}/categories.json")
    Observable<Result<CategoriesResponse>> getCategories(@Path("idBook") String idBook,
                                                         @Path("lang") String lang);

    @GET("pdd_{idBook}_lang_{lang}/{idDocument}.html")
    Observable<Result<ResponseBody>> getDocument(@Path("idBook") String idBook,
                                                 @Path("lang") String lang,
                                                 @Path("idDocument") int idDocument);
}
