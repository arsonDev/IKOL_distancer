package pl.arsonproject.ikol_distancer.models;

import com.squareup.moshi.Json;

import java.util.List;

public class MyResponse {

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

    public void setDestinationAddresses(List<String> destinationAddresses) {
        this.destinationAddresses = destinationAddresses;
    }

    public List<String> getOriginAddresses() {
        return originAddresses;
    }

    public void setOriginAddresses(List<String> originAddresses) {
        this.originAddresses = originAddresses;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
