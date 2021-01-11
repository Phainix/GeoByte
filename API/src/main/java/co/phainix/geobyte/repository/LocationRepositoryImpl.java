package co.phainix.geobyte.repository;

import co.phainix.geobyte.model.Location;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional(readOnly = true)
public class LocationRepositoryImpl implements LocationRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Location findByCoordinates(int coordinate_x, int coordinate_y) {
        System.out.println("findByCoordinates");
        Query query = entityManager.createNativeQuery("SELECT lc.* FROM locations as lc WHERE coordinate_x = ? AND coordinate_y = ?", Location.class);
        query.setParameter(1, coordinate_x);
        query.setParameter(2, coordinate_y);

        return (Location) query.getSingleResult();
    }

}
