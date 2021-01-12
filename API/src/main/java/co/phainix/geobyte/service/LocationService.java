package co.phainix.geobyte.service;

import co.phainix.geobyte.dto.model.LocationDto;
import co.phainix.geobyte.dto.response.LocationResponseDto;
import co.phainix.geobyte.model.Location;
import co.phainix.geobyte.model.OptimalRoutePath;

import java.util.List;

public interface LocationService {

    List<LocationDto> getAll();

    LocationDto get(long id);

    LocationResponseDto add(LocationDto locationDto);

    boolean remove(long id);

    LocationResponseDto update(LocationDto locationDto);

    List<OptimalRoutePath> getOptimalRoute(long origin_location_id, long destination_location_id);

}
