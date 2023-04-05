package project.autoservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.autoservice.model.Product;
import project.autoservice.model.dto.request.ProductRequestDto;
import project.autoservice.model.dto.response.ProductResponseDto;
import project.autoservice.service.ProductService;
import project.autoservice.service.mapper.ModelMapper;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper<Product, ProductResponseDto, ProductRequestDto> productMapper;

    public ProductController(ProductService productService,
                             ModelMapper<Product,
                                     ProductResponseDto,
                                     ProductRequestDto> productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.save(productMapper.toModel(productRequestDto));
        return productMapper.toDto(product);
    }

    @PostMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ProductRequestDto requestDto) {
        Product product = productMapper.toModel(requestDto);
        product.setId(id);
        productService.save(product);
    }
}
