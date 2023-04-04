package project.autoservice.service;

import project.autoservice.model.Order;
import project.autoservice.model.Owner;

import java.util.List;

public interface OwnerService {
    Owner save(Owner owner);

    Owner findById(Long id);

    List<Order> findOrdersById(Long id);
}
