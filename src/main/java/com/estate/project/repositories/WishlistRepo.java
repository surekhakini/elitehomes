package com.estate.project.repositories;

import com.estate.project.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishlistRepo extends JpaRepository<Wishlist, Long> {


        List<Wishlist> findByUsername(String username);

        long countByPropertyId(Long propertyId); // ⭐ ADD THIS

        @Query("""
        SELECT COUNT(w)
        FROM Wishlist w
        WHERE w.propertyId IN (
            SELECT p.id
            FROM Property p
            WHERE p.sellerUsername = :sellerUsername
        )
    """)
        long countWishlistsBySeller(@Param("sellerUsername") String sellerUsername);


}