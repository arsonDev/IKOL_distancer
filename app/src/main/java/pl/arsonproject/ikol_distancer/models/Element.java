package pl.arsonproject.ikol_distancer.models;

import com.squareup.moshi.Json;

public class Element {
    @Json(name = "distance")
    private Distance distance;
    @Json(name = "duration")
    private Duration duration;
    @Json(name = "status")
    private String status;

    public Distance getDistance() {
        return distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }
}
