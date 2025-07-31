package com.praveen.ecommerce.service.impl;

import com.praveen.ecommerce.dto.ProductDto;
import com.praveen.ecommerce.entity.Product;
import com.praveen.ecommerce.repository.ProductRepository;
import com.praveen.ecommerce.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream().map(this::transformToDto).collect(Collectors.toList());
    }

    private ProductDto transformToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        productDto.setProductId(product.getId());
        return productDto;
    }
}
