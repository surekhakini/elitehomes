package com.estate.project.controller;

import com.estate.project.models.Wishlist;
import com.estate.project.repositories.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin(origins = "*")
public class WishlistController {

    @Autowired
    private WishlistRepo repository;

    // ADD TO WISHLIST
    @PostMapping
    public Wishlist addToWishlist(@RequestBody Wishlist wishlist) {
        wishlist.setCreatedAt(LocalDateTime.now());
        return repository.save(wishlist);
    }


    // GET WISHLIST BY USERNAME
    @GetMapping("/user/{username}")
    public List<Wishlist> getWishlistByUser(@PathVariable String username) {
        return repository.findByUsername(username);
    }

    // DELETE FROM WISHLIST
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }



}
