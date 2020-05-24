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
                    .build();

            Request newRequest = chain.request()
                    .newBuilder()
                    .url(url)
                    .addHeader("x-rapidapi-host", "distance-calculator.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "47b4cd7f1bmshda90762460e22ebp1c5fc3jsn1e44df9c0bee")
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
                 .baseUrl("https://distance-calculator.p.rapidapi.com/v1/")
                 .addConverterFactory(MoshiConverterFactory.create())
                 .build();
    }

    public static DistanceApi api = retrofic().create(DistanceApi.class);
}
