package com.sistemas.senior.stockcontrol.repository;

import com.sistemas.senior.stockcontrol.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

}
