package project.autoservice.service;

import java.util.List;
import project.autoservice.model.Order;
import project.autoservice.model.Owner;

public interface OwnerService {
    Owner save(Owner owner);

    Owner findById(Long id);

    List<Order> findOrdersById(Long id);
}
