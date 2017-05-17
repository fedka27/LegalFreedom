package legalFreedom.java.injection.api;

import android.content.Context;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import legalFreedom.R;
import legalFreedom.java.model.api.Api;
import legalFreedom.java.util.ConnectionUtil;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@ApiScope
@Module
public class ApiModule {
    private static final int CONNECT_TIMEOUT_SECONDS = 15;
    private static final int READ_TIMEOUT_SECONDS = 15;
    private static final int WRITE_TIMEOUT_SECONDS = 15;

    @ApiScope
    @Provides
    Api provideApiInterface(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @ApiScope
    @Provides
    Retrofit provideRetrofit(Context context, Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @ApiScope
    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @ApiScope
    @Provides
    OkHttpClient provideOkHttpClient(Interceptor cacheInterceptor,
                                               HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(cacheInterceptor)
                .addInterceptor(loggingInterceptor)
                .authenticator((route, response) -> response.request().newBuilder()
                        .header("Authorization", "")
                        .build())
                .retryOnConnectionFailure(true)
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();
    }


    @ApiScope
    @Provides
    Interceptor provideCacheInterceptor(Context context) {
        return chain -> {
            int maxAge = 60 * 5; // read from cache for 5 minute
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            Request originalRequest = chain.request();
            String cacheHeaderValue = ConnectionUtil.isOnline(context)
                    ? "public, max-age=" + maxAge
                    : "public, only-if-cached, max-stale=" + maxStale;
            Request request = originalRequest.newBuilder().build();
            okhttp3.Response response = chain.proceed(request);
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", cacheHeaderValue)
                    .build();
        };
    }

    @ApiScope
    @Provides
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logInterceptor;
    }
}
