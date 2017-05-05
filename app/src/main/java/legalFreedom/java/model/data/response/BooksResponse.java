package legalFreedom.java.model.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import legalFreedom.java.model.data.dto.Book;


public class BooksResponse extends ResultResponse{
    @SerializedName("books")
    private List<Book> books;

    public BooksResponse(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
}
