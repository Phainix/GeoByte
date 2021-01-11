package co.phainix.geobyte.repository;

import co.phainix.geobyte.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByName(String name);

    Location findFirstByCoordinateXAndCoordinateY(int coordinate_x, int coordinate_y);

}
