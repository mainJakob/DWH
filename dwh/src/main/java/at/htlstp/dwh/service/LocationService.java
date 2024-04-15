package at.htlstp.dwh.service;

import at.htlstp.dwh.repository.LocationRepo;
import at.htlstp.dwh.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocationService {

    private final LocationRepo locationRepository;

    @Autowired
    public LocationService(LocationRepo locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }


}
