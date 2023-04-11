package project.autoservice.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.autoservice.model.Product;
import project.autoservice.repository.ProductRepository;
import project.autoservice.service.ProductService;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find product by id: " + id));
    }

    @Override
    public List<Product> findAllByIds(List<Long> ids) {
        return productRepository.findAllByIdIn(ids);
    }
}
