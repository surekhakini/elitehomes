package com.estate.project.controller;

import com.estate.project.models.Property;
import com.estate.project.repositories.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyRepo propertyRepo;

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyRepo.findAll();
    }

    @GetMapping("/search")
    public List<Property> searchByCity(@RequestParam String city) {
        return propertyRepo.findByCityContainingIgnoreCase(city);
    }

    @PostMapping
    public Property addProperty(
            @RequestParam String title,
            @RequestParam String city,
            @RequestParam String type,
            @RequestParam double price,
            @RequestParam String description,
            @RequestParam String sellerUsername,
            @RequestParam MultipartFile image
    ) throws Exception {

        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path uploadPath = Paths.get("src/main/resources/static/images");

        Files.createDirectories(uploadPath);
        Files.copy(image.getInputStream(), uploadPath.resolve(fileName),
                StandardCopyOption.REPLACE_EXISTING);

        Property property = new Property();
        property.setTitle(title);
        property.setCity(city);
        property.setType(type);
        property.setPrice(price);
        property.setDescription(description);
        property.setImageUrl("images/" + fileName);
        property.setSellerUsername(sellerUsername);

        return propertyRepo.save(property);
    }


    @PutMapping("/{id}/view")
    public ResponseEntity<String> incrementView(@PathVariable Long id) {

        Property property = propertyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        property.setViews(property.getViews() + 1);
        propertyRepo.save(property);

        return ResponseEntity.ok("View count updated");
    }

    @GetMapping("/{id}")
    public Property getPropertyById(@PathVariable Long id) {
        return propertyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
    }

}
