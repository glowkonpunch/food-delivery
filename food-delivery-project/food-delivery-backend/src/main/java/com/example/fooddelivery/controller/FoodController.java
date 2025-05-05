
package com.example.fooddelivery.controller;

import com.example.fooddelivery.model.Food;
import com.example.fooddelivery.repository.FoodRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@CrossOrigin(origins="http://localhost:4200")
public class FoodController {
    private final FoodRepository foodRepository;
    public FoodController(FoodRepository foodRepository){
        this.foodRepository = foodRepository;
    }

    @GetMapping("")
    public List<Food> list(){
        return foodRepository.findAll();
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Food add(@RequestBody Food food){
        return foodRepository.save(food);
    }
}
