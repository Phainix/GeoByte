package co.phainix.geobyte.controller.v1.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationRequest {

    @NotNull(message = "Coordinate X is required")
    @Min(value=0, message="Coordinate X must be equal or greater than 0")
    @Max(value=40, message="Coordinate X must be equal or less than 40")
    private int coordinate_x;

    @NotNull(message = "Coordinate Y is required")
    @Min(value=0, message="Coordinate Y must be equal or greater than 0")
    @Max(value=40, message="Coordinate Y must be equal or less than 40")
    private int coordinate_y;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Clearing cost is required")
    @Min(value=25, message="Coordinate Y must be equal or greater than 25")
    @Max(value=100, message="Coordinate Y must be equal or less than 100")
    private int clearing_cost;

    public LocationRequest() {
    }

    public int getCoordinate_x() {
        return coordinate_x;
    }

    public void setCoordinate_x(int coordinate_x) {
        this.coordinate_x = coordinate_x;
    }

    public int getCoordinate_y() {
        return coordinate_y;
    }

    public void setCoordinate_y(int coordinate_y) {
        this.coordinate_y = coordinate_y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClearing_cost() {
        return clearing_cost;
    }

    public void setClearing_cost(int clearing_cost) {
        this.clearing_cost = clearing_cost;
    }
}
