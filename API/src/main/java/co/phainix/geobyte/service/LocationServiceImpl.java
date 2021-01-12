package co.phainix.geobyte.service;

import co.phainix.geobyte.dto.mapper.LocationMapper;
import co.phainix.geobyte.dto.model.LocationDto;
import co.phainix.geobyte.dto.response.LocationResponseDto;
import co.phainix.geobyte.exception.EntityType;
import co.phainix.geobyte.exception.ExceptionType;
import co.phainix.geobyte.exception.GeoByteException;
import co.phainix.geobyte.model.GeoByteStatus;
import co.phainix.geobyte.model.Location;
import co.phainix.geobyte.model.OptimalRouteLocation;
import co.phainix.geobyte.model.OptimalRoutePath;
import co.phainix.geobyte.repository.LocationRepository;
import co.phainix.geobyte.util.OptimalRoute;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.*;

import static co.phainix.geobyte.exception.EntityType.LOCATION;
import static co.phainix.geobyte.exception.EntityType.ROUTE;
import static co.phainix.geobyte.exception.ExceptionType.*;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<LocationDto> getAll() {
        List<LocationDto> locationDtoList = new ArrayList<LocationDto>();
        List<Location> locations = locationRepository.findAll();
        for (Location location: locations) {
            locationDtoList.add(LocationMapper.toLocationDto(location));
        }

        return locationDtoList;
    }

    @Override
    public LocationDto get(long id) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()) {
            return LocationMapper.toLocationDto(location.get());
        }
        throw exception(LOCATION, ENTITY_NOT_FOUND);
    }

    @Override
    public LocationResponseDto add(LocationDto locationDto) {
        Location location = null;
        location = locationRepository.findFirstByCoordinateXAndCoordinateY(locationDto.getCoordinate_x(), locationDto.getCoordinate_y());
        if (location == null) {
            location = new Location();
            location.setCoordinate_x(locationDto.getCoordinate_x());
            location.setCoordinate_y(locationDto.getCoordinate_y());
            location.setName(locationDto.getName());
            location.setClearing_cost(locationDto.getClearing_cost());
            location.setGeoByteStatus(GeoByteStatus.ACTIVE);

            Location savedLocation = locationRepository.save(location);
            if(savedLocation == null) {
                throw exception(LOCATION, ENTITY_EXCEPTION, locationDto.getName());
            }
            return LocationMapper.toLocationResponseDto(savedLocation);
        }
        throw exception(LOCATION, DUPLICATE_ENTITY, locationDto.getName());
    }

    @Override
    public boolean remove(long id) {
        get(id);
        locationRepository.deleteById(id);

        Optional<Location> location = locationRepository.findById(id);
        return location.isPresent() == false;
    }

    @Override
    public LocationResponseDto update(LocationDto locationDto) {
        Optional<Location> location = locationRepository.findById(locationDto.getId());
        if (location.isPresent()) {
            Location locationModel = location.get();
            locationModel.setCoordinate_x(locationDto.getCoordinate_x());
            locationModel.setCoordinate_y(locationDto.getCoordinate_y());
            locationModel.setName(locationDto.getName());
            locationModel.setClearing_cost(locationDto.getClearing_cost());

            return LocationMapper.toLocationResponseDto(locationRepository.save(locationModel));
        }
        throw exception(LOCATION, ENTITY_NOT_FOUND);
    }

    @Override
    public List<OptimalRoutePath> getOptimalRoute(long origin_location_id, long destination_location_id) {
        Map<Integer, OptimalRouteLocation> optimalRouteLocationList = new HashMap<>();
        List<Location> locations = locationRepository.findAll();

        if(locations.size() < 3)
            throw exception(ROUTE, ENTITY_EXCEPTION);

        int count = 0;
        for (Location location : locations) {
            optimalRouteLocationList.put(count++, new OptimalRouteLocation(location.getCoordinate_x(), location.getCoordinate_y(), location.getClearing_cost(), (int) location.getId()));
        }

        System.out.println(optimalRouteLocationList.entrySet().toString());
        System.out.println(origin_location_id + " " + destination_location_id);

        Optional<Location> origin = locationRepository.findById(origin_location_id);
        Optional<Location> destination = locationRepository.findById(destination_location_id);

        if((origin.isPresent() && destination.isPresent()) == false)
            throw exception(LOCATION, ENTITY_NOT_FOUND);

        OptimalRoute optimalRoute = new OptimalRoute(optimalRouteLocationList, (int) origin_location_id, (int) destination_location_id);

        List<OptimalRoutePath> optimalRoutePaths = optimalRoute.getOptimisedPath();
        System.out.println("Optimal route is " + optimalRoutePaths.get(0).getPath());

        return optimalRoutePaths;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return GeoByteException.throwException(entityType, exceptionType, args);
    }
}
