package legalFreedom.java.model.data.dto;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable{
    @SerializedName("id")
    private float id;
    @SerializedName("name")
    private String name;
    @SerializedName("documentId")
    private int documentId;



    public float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDocumentId() {
        return documentId;
    }
}
