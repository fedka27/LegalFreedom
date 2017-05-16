package legalFreedom.java.model.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import legalFreedom.java.model.data.dto.Category;

public class CategoriesResponse extends ResultResponse{
    @SerializedName("cat")
    private List<Category> cat;

    private CategoriesResponse(Builder builder) {
        cat = builder.cat;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(CategoriesResponse copy) {
        Builder builder = new Builder();
        builder.cat = copy.cat;
        return builder;
    }

    public List<Category> getCat() {
        return cat;
    }


    public static final class Builder {
        private List<Category> cat;

        private Builder() {
        }

        public Builder cat(List<Category> val) {
            cat = val;
            return this;
        }

        public CategoriesResponse build() {
            return new CategoriesResponse(this);
        }
    }
}
