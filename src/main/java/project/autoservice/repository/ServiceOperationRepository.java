package project.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.autoservice.model.ServiceOperation;

@Repository
public interface ServiceOperationRepository extends JpaRepository<ServiceOperation, Long> {
}
