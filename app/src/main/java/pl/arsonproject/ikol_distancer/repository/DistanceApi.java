package pl.arsonproject.ikol_distancer.repository;

import pl.arsonproject.ikol_distancer.models.Location;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DistanceApi {
    @GET("distancematrix/json")
    Call<Location> calculateDistance(@Query(value = "origins") String origins,
                                     @Query(value = "destinations") String destination);
}
