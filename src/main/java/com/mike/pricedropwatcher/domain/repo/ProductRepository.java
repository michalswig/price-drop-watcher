package com.mike.pricedropwatcher.domain.repo;

import com.mike.pricedropwatcher.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByActiveTrue();
}
