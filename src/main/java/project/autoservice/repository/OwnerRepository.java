package project.autoservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.autoservice.model.Order;
import project.autoservice.model.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query("SELECT ow.orders FROM Owner ow WHERE ow.id = ?1")
    List<Order> findOrdersByOwnerId(Long ownerId);
}
