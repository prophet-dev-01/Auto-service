package project.autoservice.mapper.impl;

import org.springframework.stereotype.Component;
import project.autoservice.mapper.ModelMapper;
import project.autoservice.model.Product;
import project.autoservice.model.dto.request.ProductRequestDto;
import project.autoservice.model.dto.response.ProductResponseDto;

@Component
public class ProductMapper implements ModelMapper<Product, ProductResponseDto, ProductRequestDto> {
    @Override
    public Product toModel(ProductRequestDto request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        return product;
    }

    @Override
    public ProductResponseDto toDto(Product model) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(model.getId());
        responseDto.setName(model.getName());
        responseDto.setPrice(model.getPrice());
        return responseDto;
    }
}
