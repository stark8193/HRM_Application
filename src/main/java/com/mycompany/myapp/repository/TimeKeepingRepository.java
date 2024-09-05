package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TimeKeeping;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TimeKeeping entity.
 */
@Repository
public interface TimeKeepingRepository extends JpaRepository<TimeKeeping, Long>, JpaSpecificationExecutor<TimeKeeping> {
    default Optional<TimeKeeping> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<TimeKeeping> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<TimeKeeping> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct timeKeeping from TimeKeeping timeKeeping left join fetch timeKeeping.employee",
        countQuery = "select count(distinct timeKeeping) from TimeKeeping timeKeeping"
    )
    Page<TimeKeeping> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct timeKeeping from TimeKeeping timeKeeping left join fetch timeKeeping.employee")
    List<TimeKeeping> findAllWithToOneRelationships();

    @Query("select timeKeeping from TimeKeeping timeKeeping left join fetch timeKeeping.employee where timeKeeping.id =:id")
    Optional<TimeKeeping> findOneWithToOneRelationships(@Param("id") Long id);
}
