package project.autoservice.service;

import project.autoservice.model.Product;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);
}
