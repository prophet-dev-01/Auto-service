package project.autoservice.service;

import project.autoservice.model.Master;
import project.autoservice.model.Order;

import java.math.BigDecimal;
import java.util.List;

public interface MasterService {
    Master save(Master master);

    Master findById(Long id);

    List<Order> findOrdersByMasterId(Long id);

    BigDecimal issuanceOfSalary(Long id);
}
