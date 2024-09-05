package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.LeaveRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LeaveRequest entity.
 */
@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long>, JpaSpecificationExecutor<LeaveRequest> {
    default Optional<LeaveRequest> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<LeaveRequest> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<LeaveRequest> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct leaveRequest from LeaveRequest leaveRequest left join fetch leaveRequest.employee left join fetch leaveRequest.leaveType",
        countQuery = "select count(distinct leaveRequest) from LeaveRequest leaveRequest"
    )
    Page<LeaveRequest> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct leaveRequest from LeaveRequest leaveRequest left join fetch leaveRequest.employee left join fetch leaveRequest.leaveType"
    )
    List<LeaveRequest> findAllWithToOneRelationships();

    @Query(
        "select leaveRequest from LeaveRequest leaveRequest left join fetch leaveRequest.employee left join fetch leaveRequest.leaveType where leaveRequest.id =:id"
    )
    Optional<LeaveRequest> findOneWithToOneRelationships(@Param("id") Long id);
}
