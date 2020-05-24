package pl.arsonproject.ikol_distancer.models;

import com.squareup.moshi.Json;

public class Duration {
    @Json(name = "text")
    private String text;
    @Json(name = "value")
    private Integer value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
