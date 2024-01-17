package com.example.springboot.repository;
import com.example.springboot.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByNameContaining(String name);
    List<Product> findAllByPriceBetween(double min , double max);
    List<Product> findAllByOrderByPrice();
    Page<Product> findAll(Pageable pageable);
}
