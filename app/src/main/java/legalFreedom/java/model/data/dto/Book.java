package legalFreedom.java.model.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Book implements Serializable{
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("bookId")
    private String bookId;
    @SerializedName("lang")
    private String lang;



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

    public String getLang() {
        return lang;
    }
}
