package co.phainix.geobyte.dto.model;

import co.phainix.geobyte.model.GeoByteStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Date;


@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private String email;
    private String password;
    private String name;
    private Date date_created;
    private GeoByteStatus geoByteStatus;

    public UserDto() {
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

    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", status='" + geoByteStatus + '\'' +
                ", date_created='" + date_created + '\'' +
                '}';
    }
}
