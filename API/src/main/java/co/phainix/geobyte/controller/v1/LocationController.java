package co.phainix.geobyte.controller.v1;

import co.phainix.geobyte.controller.v1.request.LocationRequest;
import co.phainix.geobyte.dto.model.LocationDto;
import co.phainix.geobyte.dto.response.LocationResponseDto;
import co.phainix.geobyte.dto.response.Response;
import co.phainix.geobyte.model.GeoByteStatus;
import co.phainix.geobyte.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/getAll")
    public Response getAll() {
        Response resp =  Response.ok();
        resp.setData(getAllLocations());
        return resp;
    }

    private List<LocationDto> getAllLocations() {
        return locationService.getAll();
    }

    @PostMapping("/add")
    public Response add(@RequestBody @Valid LocationRequest locationRequest) {
        Response resp =  Response.ok();
        resp.setData(addLocation(locationRequest));
        return resp;
    }

    private LocationResponseDto addLocation(LocationRequest locationRequest) {
        LocationDto locationDto = new LocationDto();
        locationDto.setCoordinate_y(locationRequest.getCoordinate_y());
        locationDto.setCoordinate_x(locationRequest.getCoordinate_x());
        locationDto.setClearing_cost(locationRequest.getClearing_cost());
        locationDto.setName(locationRequest.getName());
        locationDto.setGeoByteStatus(GeoByteStatus.ACTIVE);

        return locationService.add(locationDto);
    }
}
