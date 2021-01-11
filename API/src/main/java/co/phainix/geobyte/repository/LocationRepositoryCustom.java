package co.phainix.geobyte.repository;

import co.phainix.geobyte.model.Location;

public interface LocationRepositoryCustom {

     Location findByCoordinates(int coordinate_x, int coordinate_y);

}
