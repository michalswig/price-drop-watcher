package com.mike.pricedropwatcher.domain.repo;

import com.mike.pricedropwatcher.domain.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  List<Product> findByActiveTrue();
}
