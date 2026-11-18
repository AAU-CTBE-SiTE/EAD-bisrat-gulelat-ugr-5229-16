
package com.example.inventorycrud.repository;

import com.example.inventorycrud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    List<Product> findByCategoryIgnoreCase(String category);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByQuantityLessThan(Integer quantity);

    @Query("SELECT AVG(p.price) FROM Product p")
    Double findAveragePrice();
}
