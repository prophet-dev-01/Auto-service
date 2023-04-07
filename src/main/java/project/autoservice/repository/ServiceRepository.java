package project.autoservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.autoservice.model.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findAllByMasterId(Long masterId);

    List<Service> findAllByIdIn(List<Long> ids);
}
