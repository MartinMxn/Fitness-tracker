package demo.service.impl;

import demo.service.LocationService;
import demo.domain.Location;
import demo.domain.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    //field injection / constructor inj)
    private LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> saveRunningLocations(List<Location> runningLocations) {
        List<Location> save = locationRepository.saveAll(runningLocations);
        return save;
    }

    @Override
    public void deleteAll() {
        locationRepository.deleteAll();
    }

    @Override
    public Page<Location> findByMovementType(String movementType, Pageable pageable) {
        return locationRepository.findByRunnerMovementType(Location.RunnerMovementType.valueOf(movementType), pageable);
    }
}
