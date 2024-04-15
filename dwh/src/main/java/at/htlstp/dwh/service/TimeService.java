package at.htlstp.dwh.service;

import at.htlstp.dwh.repository.TimeRepo;
import at.htlstp.dwh.model.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TimeService {

    private final TimeRepo timeRepository;

    @Autowired
    public TimeService(TimeRepo timeRepository) {
        this.timeRepository = timeRepository;
    }

    public List<Time> findAllTimes() {
        return timeRepository.findAll();
    }


}
