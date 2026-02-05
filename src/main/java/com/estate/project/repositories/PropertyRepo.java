package com.estate.project.repositories;

import com.estate.project.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PropertyRepo extends JpaRepository<Property, Long> {
    List<Property> findByCityContainingIgnoreCase(String city);
    List<Property> findBySellerUsername(String sellerUsername);

}

