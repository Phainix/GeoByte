package co.phainix.geobyte.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private GeoByteStatus geoByteStatus;

    @Column(name = "date_created", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Date date_created;

    public User() {
    }

    public User(String name, String email, GeoByteStatus geoByteStatus) {
        this.name = name;
        this.email = email;
        this.geoByteStatus = geoByteStatus;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + geoByteStatus +
                ", date_created=" + date_created +
                '}';
    }
}
