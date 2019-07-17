package demo.service;

import demo.domain.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LocationService {
    List<Location> saveRunningLocations(List<Location> runningLocations);

    void deleteAll();
    //String to hide impl of enum
    Page<Location> findByMovementType(String movementType, Pageable pageable);
}
