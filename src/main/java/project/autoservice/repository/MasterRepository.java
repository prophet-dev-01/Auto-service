package project.autoservice.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.autoservice.model.Master;
import project.autoservice.model.ServiceOperation;

@Repository
public interface MasterRepository extends JpaRepository<Master, Long> {
    @Query("SELECT m.serviceOperations "
            + "FROM Master m LEFT JOIN m.serviceOperations as serv "
            + "LEFT JOIN serv.order WHERE m.id = ?1")
    Optional<List<ServiceOperation>> findAllServiceById(Long id);
}
