package pl.arsonproject.ikol_distancer.models;

import com.squareup.moshi.Json;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public boolean checkPoints(String point) {
        boolean result = false;
        try {
            Pattern pointsPattern = Pattern.compile("(^(-?\\d+(\\.(\\d+))?),\\s*(-?\\d+(\\.(\\d+))?))");
            Matcher matcher = pointsPattern.matcher(point);
            if (matcher.find()) {
                Pattern firstCoordPattern = Pattern.compile("(?<=^)(-?\\d+(\\.(\\d+))?)(?=,)");
                Matcher firstCoordMatcher = firstCoordPattern.matcher(matcher.group(0));
                if (firstCoordMatcher.find()) {
                    double lat = Double.valueOf(firstCoordMatcher.group(0));
                    if (lat >= -90 && lat <= 90)
                        result = true;
                    else
                        result = false;
                }

                if (result) {
                    Pattern secCoordPattern = Pattern.compile("(?<=,)(\\s?-?\\d+(\\.(\\d+))?)(?=)");
                    Matcher secCoordMatcher = secCoordPattern.matcher(matcher.group(0));
                    if (secCoordMatcher.find()) {
                        double lon = Double.valueOf(secCoordMatcher.group(0));
                        if (lon >= -180 && lon <= 180)
                            result = true;
                        else
                            result = false;
                    }
                }
            } else {
                Pattern specialPattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");
                result = !point.isEmpty() && !specialPattern.matcher(point).find();
            }
        } catch (Exception e) {

        }
        return result;
    }
}
