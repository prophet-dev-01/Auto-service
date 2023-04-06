package project.autoservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.autoservice.model.ServiceOperation;

@Repository
public interface ServiceOperationRepository extends JpaRepository<ServiceOperation, Long> {
    List<ServiceOperation> findAllByMasterId(Long id);
}
