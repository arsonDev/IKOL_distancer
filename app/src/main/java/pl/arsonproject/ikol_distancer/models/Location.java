package pl.arsonproject.ikol_distancer.models;

import com.squareup.moshi.Json;

import java.util.List;

public class Location {

    @Json(name = "destination_addresses")
    private List<String> destinationAddresses = null;
    @Json(name = "origin_addresses")
    private List<String> originAddresses = null;
    @Json(name = "rows")
    private List<Row> rows = null;
    @Json(name = "status")
    private String status;

    public List<String> getDestinationAddresses() {
        return destinationAddresses;
    }

    public List<String> getOriginAddresses() {
        return originAddresses;
    }

    public List<Row> getRows() {
        return rows;
    }

    public String getStatus() {
        return status;
    }
}
