package com.praveen.ecommerce.service;

import com.praveen.ecommerce.dto.ProductDto;
import java.util.List;

public interface IProductService {
    List<ProductDto> getProducts();
}
