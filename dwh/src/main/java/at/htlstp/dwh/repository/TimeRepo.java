package at.htlstp.dwh.repository;

import at.htlstp.dwh.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepo extends JpaRepository<Time, Long> {

}
