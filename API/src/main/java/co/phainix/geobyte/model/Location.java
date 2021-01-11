package co.phainix.geobyte.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "coordinate_x")
    private int coordinateX;

    @Column(name = "coordinate_y")
    private int coordinateY;

    @Column(name = "name")
    private String name;

    @Column(name = "clearing_cost")
    private int clearing_cost;

    @Column(name = "created_by")
    private int created_by;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private GeoByteStatus geoByteStatus;

    @Column(name = "date_created", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date_created;

    @Column(name = "date_updated", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date date_updated;

    public Location() {
    }

    public Location(int coordinateX, int coordinateY, String name, int clearing_cost, int created_by) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.name = name;
        this.clearing_cost = clearing_cost;
        this.created_by = created_by;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoByteStatus getGeoByteStatus() {
        return geoByteStatus;
    }

    public void setGeoByteStatus(GeoByteStatus geoByteStatus) {
        this.geoByteStatus = geoByteStatus;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public int getCoordinate_x() {
        return coordinateX;
    }

    public void setCoordinate_x(int coordinate_x) {
        this.coordinateX = coordinate_x;
    }

    public int getCoordinate_y() {
        return coordinateY;
    }

    public void setCoordinate_y(int coordinate_y) {
        this.coordinateY = coordinate_y;
    }

    public int getClearing_cost() {
        return clearing_cost;
    }

    public void setClearing_cost(int clearing_cost) {
        this.clearing_cost = clearing_cost;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public Date getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(Date date_updated) {
        this.date_updated = date_updated;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", coordinate_x=" + coordinateX +
                ", coordinate_y=" + coordinateY +
                ", name='" + name + '\'' +
                ", clearing_cost=" + clearing_cost +
                ", created_by=" + created_by +
                ", status=" + geoByteStatus +
                ", date_created=" + date_created +
                ", date_updated=" + date_updated +
                '}';
    }
}
