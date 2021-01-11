package co.phainix.geobyte.dto.mapper;

import co.phainix.geobyte.dto.model.LocationDto;
import co.phainix.geobyte.dto.response.LocationResponseDto;
import co.phainix.geobyte.model.Location;

public class LocationMapper {

    public static LocationDto toLocationDto(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(location.getId());
        locationDto.setCoordinate_x(location.getCoordinate_x());
        locationDto.setCoordinate_y(location.getCoordinate_y());
        locationDto.setClearing_cost(location.getClearing_cost());
        locationDto.setName(location.getName());
        locationDto.setGeoByteStatus(location.getGeoByteStatus());
        return locationDto;
    }

    public static LocationResponseDto toLocationResponseDto(Location location) {
        LocationResponseDto locationResponseDto = new LocationResponseDto();
        locationResponseDto.setId(location.getId());
        locationResponseDto.setCoordinate_x(location.getCoordinate_x());
        locationResponseDto.setCoordinate_y(location.getCoordinate_y());
        locationResponseDto.setClearing_cost(location.getClearing_cost());
        locationResponseDto.setName(location.getName());
        locationResponseDto.setGeoByteStatus(location.getGeoByteStatus());
        return locationResponseDto;
    }

}
