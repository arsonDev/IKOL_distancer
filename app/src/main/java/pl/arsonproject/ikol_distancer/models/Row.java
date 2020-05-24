package pl.arsonproject.ikol_distancer.models;

import com.squareup.moshi.Json;

import java.util.List;

public class Row {
    @Json(name = "elements")
    private List<Element> elements = null;

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
