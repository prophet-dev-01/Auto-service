package project.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.autoservice.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long > {
}
