package co.phainix.geobyte.dto.model;

import co.phainix.geobyte.model.GeoByteStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Date;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDto {

    private long id;
    private int coordinate_x;
    private int coordinate_y;
    private String name;
    private int clearing_cost;
    private Date date_created;
    private GeoByteStatus geoByteStatus;

    public LocationDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public GeoByteStatus getGeoByteStatus() {
        return geoByteStatus;
    }

    public void setGeoByteStatus(GeoByteStatus geoByteStatus) {
        this.geoByteStatus = geoByteStatus;
    }

    @Override
    public String toString() {
        return "LocationDto{" +
                "id=" + id +
                ", coordinate_x=" + coordinate_x +
                ", coordinate_y=" + coordinate_y +
                ", name='" + name + '\'' +
                ", clearing_cost=" + clearing_cost +
                ", date_created=" + date_created +
                ", geoByteStatus=" + geoByteStatus +
                '}';
    }
}
