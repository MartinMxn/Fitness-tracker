package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface RunningInformationRepository extends JpaRepository<RunningInformation, Long> {

    // spring pagination will generate SQL based on your method name
    Page<RunningInformation> findByHeartRateGreaterThan(
            @Param("heartRate") int heartRate,
            Pageable pageable
    );

    Page<RunningInformation> findByHeartRate(
            @Param("heartRate") int heartRate,
            Pageable pageable
    );

}
