package project.autoservice.service;

import java.math.BigDecimal;
import project.autoservice.model.Master;

public interface MasterService {
    Master save(Master master);

    Master findById(Long id);

    BigDecimal issuanceOfSalary(Long id);
}
