package project.autoservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.autoservice.model.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findAllByMasterId(Long masterId);

    List<Service> findAllByIdIn(List<Long> ids);

    @Transactional
    @Modifying
    @Query("UPDATE Service SET status = :status where id = :id")
    void updateStatus(Service.PaymentStatus status, Long id);
}
