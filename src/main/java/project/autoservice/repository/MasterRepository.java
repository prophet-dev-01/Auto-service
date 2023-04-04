package project.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.autoservice.model.Master;
import project.autoservice.model.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface MasterRepository extends JpaRepository<Master, Long> {
    @Query("SELECT m.completedOrder FROM Master m WHERE m.id = ?1")
    Optional<List<Order>> findOrdersByMasterId(Long id);
}
