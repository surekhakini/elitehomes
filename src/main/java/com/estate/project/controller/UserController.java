package com.estate.project.controller;

import com.estate.project.dto.LoginDto;
import com.estate.project.models.User;
import com.estate.project.repositories.UserRepo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserRepo userRepo;

    // ✅ THIS METHOD IS NEW
    @GetMapping("/")
    public void openLoginPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login.html");
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userRepo.save(user);
        return "Signup Successful";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto u) {
        User user = userRepo.findByUsername(u.getUsername());

        if (user == null) {
            return "User not found";
        }

        if (!u.getPassword().equals(user.getPassword())) {
            return "Password Incorrect";
        }

        if (!u.getRole().equalsIgnoreCase(user.getRole())) {
            return "Invalid role selected";
        }

        return String.valueOf(user.getId());
    }
}
