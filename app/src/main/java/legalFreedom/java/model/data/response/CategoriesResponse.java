package legalFreedom.java.model.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import legalFreedom.java.model.data.dto.Category;

public class CategoriesResponse extends ResultResponse{
    @SerializedName("cat")
    private ArrayList<Category> categoryList;

    private CategoriesResponse(Builder builder) {
        categoryList = builder.cat;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(CategoriesResponse copy) {
        Builder builder = new Builder();
        builder.cat = copy.categoryList;
        return builder;
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }


    public static final class Builder {
        private ArrayList<Category> cat;

        private Builder() {
        }

        public Builder cat(ArrayList<Category> val) {
            cat = val;
            return this;
        }

        public CategoriesResponse build() {
            return new CategoriesResponse(this);
        }
    }
}
