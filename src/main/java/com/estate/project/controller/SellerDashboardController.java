package com.estate.project.controller;

import com.estate.project.models.Property;
import com.estate.project.repositories.PropertyRepo;
import com.estate.project.repositories.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seller")
@CrossOrigin
public class SellerDashboardController {

    @Autowired
    private PropertyRepo propertyRepository;

    @GetMapping("/dashboard/{username}")
    public Map<String, Object> getDashboard(@PathVariable String username) {

        List<Property> properties =
                propertyRepository.findBySellerUsername(username);

        int totalProperties = properties.size();
        // ⭐ STEP 3C: set wishlist count per property
        for (Property p : properties) {
            int wishlistCount = (int) wishlistRepository.countByPropertyId(p.getId());
            p.setWishlistCount(wishlistCount);
        }


        int totalViews = properties.stream().mapToInt(Property::getViews).sum();
        long totalWishlists =
                wishlistRepository.countWishlistsBySeller(username);

        int totalContacts = properties.stream().mapToInt(Property::getContactCount).sum();

        Map<String, Object> response = new HashMap<>();
        response.put("totalProperties", totalProperties);
        response.put("totalViews", totalViews);
        response.put("totalWishlists", totalWishlists);
        response.put("totalContacts", totalContacts);
        response.put("properties", properties);

        return response;
    }
    @Autowired
    private WishlistRepo wishlistRepository;

}
