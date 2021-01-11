package co.phainix.geobyte.service;

import co.phainix.geobyte.dto.mapper.LocationMapper;
import co.phainix.geobyte.dto.model.LocationDto;
import co.phainix.geobyte.dto.response.LocationResponseDto;
import co.phainix.geobyte.exception.EntityType;
import co.phainix.geobyte.exception.ExceptionType;
import co.phainix.geobyte.exception.GeoByteException;
import co.phainix.geobyte.model.GeoByteStatus;
import co.phainix.geobyte.model.Location;
import co.phainix.geobyte.repository.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static co.phainix.geobyte.exception.EntityType.LOCATION;
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
        locationRepository.deleteById(id);

        Optional<Location> location = Optional.ofNullable(locationRepository.getOne(id));
        return !location.isPresent();
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

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return GeoByteException.throwException(entityType, exceptionType, args);
    }
}
