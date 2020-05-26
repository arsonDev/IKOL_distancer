package pl.arsonproject.ikol_distancer.models;

import com.squareup.moshi.Json;


public class Distance {
    @Json(name = "text")
    private String text;
    @Json(name = "value")
    private Integer value;

    public String getText() {
        return text;
    }

    public Integer getValue() {
        return value;
    }
}
