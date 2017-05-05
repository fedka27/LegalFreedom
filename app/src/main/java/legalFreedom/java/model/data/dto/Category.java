package legalFreedom.java.model.data.dto;


import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("id")
    private float id;
    @SerializedName("name")
    private String name;
    @SerializedName("path")
    private String path;



    public float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
