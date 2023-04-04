package project.autoservice.service.impl;

import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import project.autoservice.model.Product;
import project.autoservice.repository.ProductRepository;
import project.autoservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find product by id: " + id));
    }
}
