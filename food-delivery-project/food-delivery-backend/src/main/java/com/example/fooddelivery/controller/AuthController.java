
package com.example.fooddelivery.controller;

import com.example.fooddelivery.config.JwtUtil;
import com.example.fooddelivery.model.Role;
import com.example.fooddelivery.model.User;
import com.example.fooddelivery.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="http://localhost:4200")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder encoder, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> body){
        String username = body.get("username");
        String password = body.get("password");
        String email = body.get("email");
        if(userRepository.findByUsername(username).isPresent()){
            return ResponseEntity.badRequest().body("Username taken");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setEmail(email);
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body){
        Optional<User> userOpt = userRepository.findByUsername(body.get("username"));
        if(userOpt.isEmpty()) return ResponseEntity.status(401).body("No user");
        User user = userOpt.get();
        if(!encoder.matches(body.get("password"), user.getPassword())){
            return ResponseEntity.status(401).body("Wrong password");
        }
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok(Map.of("token",token,"role",user.getRole()));
    }
}
