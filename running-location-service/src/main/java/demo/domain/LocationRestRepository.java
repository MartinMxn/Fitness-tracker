package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

// Spring REST, no need to write yourself by old school controller
// /locations/runningId?runningId=1
@RepositoryRestResource(path = "locations", collectionResourceRel = "locations")
public interface LocationRestRepository extends PagingAndSortingRepository<Location, Long> {

    //rel - reference name
    @RestResource(path = "runningId", rel = "by-runningId")
    Page<Location> findByUnitInfoRunningId(@Param("runningId") String runningId, Pageable pageable);

}
