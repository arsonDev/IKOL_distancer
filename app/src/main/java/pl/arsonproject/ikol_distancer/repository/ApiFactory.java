package pl.arsonproject.ikol_distancer.repository;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiFactory {
    private static Interceptor authInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            HttpUrl url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", "YjgkjGs6UKVLuGL7ncGUySxO1ZBHw")
                    .build();

            Request newRequest = chain.request()
                    .newBuilder()
                    .url(url)
                    .build();

            return chain.proceed(newRequest);
        }
    };

    private static OkHttpClient distanceApi = new OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .build();

    private static Retrofit retrofic(){
         return new Retrofit.Builder()
                 .client(distanceApi)
                 .baseUrl("https://api.distancematrix.ai/maps/api/")
                 .addConverterFactory(MoshiConverterFactory.create())
                 .build();
    }

    public static DistanceApi getInstance() {
        return retrofic().create(DistanceApi.class);
    }
}
