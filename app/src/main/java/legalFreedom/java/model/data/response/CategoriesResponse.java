package legalFreedom.java.model.data.response;

import com.google.gson.annotations.SerializedName;

import legalFreedom.java.model.data.dto.Category;

import java.util.List;

public class CategoriesResponse extends ResultResponse{
    @SerializedName("cat")
    private List<Category> cat;

    public List<Category> getCat() {
        return cat;
    }
}
