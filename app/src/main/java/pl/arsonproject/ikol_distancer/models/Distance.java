package pl.arsonproject.ikol_distancer.models;

import com.squareup.moshi.Json;

public class Distance {

    @Json(name = "distance")
    public double distance;

    @Json(name = "unit")
    public String unit;

}
