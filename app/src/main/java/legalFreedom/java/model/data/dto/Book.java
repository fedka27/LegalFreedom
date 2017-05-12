package legalFreedom.java.model.data.dto;

import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("bookId")
    private String bookId;


    public Book(long id, String name, String bookId) {
        this.id = id;
        this.name = name;
        this.bookId = bookId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bookId='" + bookId + '\'' +
                '}';
    }
}
