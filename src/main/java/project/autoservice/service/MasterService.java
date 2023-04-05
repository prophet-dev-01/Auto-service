package project.autoservice.service;

import java.math.BigDecimal;
import java.util.List;
import project.autoservice.model.Master;
import project.autoservice.model.ServiceOperation;

public interface MasterService {
    Master save(Master master);

    Master findById(Long id);

    List<ServiceOperation> findAllServiceById(Long id);

    BigDecimal issuanceOfSalary(Long id);
}
