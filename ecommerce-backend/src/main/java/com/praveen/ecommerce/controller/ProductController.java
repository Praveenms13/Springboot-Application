package com.praveen.ecommerce.controller;

import com.praveen.ecommerce.dto.ProductDto;
import com.praveen.ecommerce.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;

    @GetMapping
    public List<ProductDto> getProducts() {
        return iProductService.getProducts();
    }
}
