package project.autoservice.service;

import java.util.List;
import project.autoservice.model.Product;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);

    List<Product> findAllByIds(List<Long> ids);
}
