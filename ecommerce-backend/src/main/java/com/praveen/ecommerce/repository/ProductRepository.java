package com.praveen.ecommerce.repository;

import com.praveen.ecommerce.entity.Product;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends Repository<Product, Long> {
}