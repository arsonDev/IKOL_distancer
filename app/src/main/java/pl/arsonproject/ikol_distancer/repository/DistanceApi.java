package pl.arsonproject.ikol_distancer.repository;

import pl.arsonproject.ikol_distancer.models.Distance;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DistanceApi {
    @GET("simple")
    Call<Distance> calculateDistance(@Query(value = "unit") String unit,
                                     @Query(value = "lat_1") double lat_1,
                                     @Query(value = "lat_2") double lat_2,
                                     @Query(value = "long_1") double long_1,
                                     @Query(value = "long_2") double long_2);
}
