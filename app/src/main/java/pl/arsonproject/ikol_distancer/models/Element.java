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

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
