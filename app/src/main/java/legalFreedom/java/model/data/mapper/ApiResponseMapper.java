package legalFreedom.java.model.data.mapper;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import legalFreedom.java.injection.ComponentProvider;
import legalFreedom.java.model.data.exception.ApiResponseException;
import legalFreedom.java.model.data.exception.UnauthorizedException;
import legalFreedom.java.model.data.response.ResultResponse;
import legalFreedom.java.util.L;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;


public class ApiResponseMapper<R> implements io.reactivex.functions.Function<Result<R>, R> {
    private static final int HTTP_CODE_UNAUTHORIZED = 401;
    private static final int HTTP_CODE_FORBIDDEN = 403;
    private static final int HTTP_CODE_NOT_FOUND = 404;


    @Override
    public R apply(@NonNull Result<R> responseResult) throws Exception {
        if (responseResult.isError()) {
            handleInternetException(responseResult.error());
        } else if (!responseResult.response().isSuccessful()) {
            handleHttpException(responseResult.response());
        }
        return responseResult.response().body();
    }

    private void handleInternetException(Throwable throwable) {
        throw new ApiResponseException(throwable);
    }

    private void handleHttpException(Response<R> response) {
        int responseCode = response.raw().code();
        if (responseCode == HTTP_CODE_UNAUTHORIZED || responseCode == HTTP_CODE_FORBIDDEN) {
            throw new UnauthorizedException();
        } else if (responseCode == HTTP_CODE_NOT_FOUND) {
            L.e(getClass().getSimpleName(), response.message());
            throw new ApiResponseException(response.message());
        } else {
            String errorMessage;
            try {
                String json = response.errorBody().string();
                Gson gson = ComponentProvider.getInstance().getApiComponent().getGson();
                ResultResponse resultResponse = gson.fromJson(json, ResultResponse.class);
            } catch (NullPointerException | IOException e) {
                // There is no message in error body. Take raw message.
                errorMessage = response.raw().message();
                throw new ApiResponseException(errorMessage);
            }
        }
    }

    private void handleResultException(Exception exception) {
        throw new ApiResponseException(exception.getMessage());
    }


}
