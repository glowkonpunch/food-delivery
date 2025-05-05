
package com.example.fooddelivery.controller;

import com.example.fooddelivery.model.Role;
import com.example.fooddelivery.model.User;
import com.example.fooddelivery.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin(origins="http://localhost:4200")
public class AdminController {

    private final UserRepository userRepository;
    public AdminController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/users/{id}/role")
    public User changeRole(@PathVariable Long id, @RequestBody Map<String,String> body){
        User u = userRepository.findById(id).orElseThrow();
        u.setRole(Role.valueOf(body.get("role")));
        return userRepository.save(u);
    }
}
