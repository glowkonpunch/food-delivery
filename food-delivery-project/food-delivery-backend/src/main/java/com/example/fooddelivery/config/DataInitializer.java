package com.example.fooddelivery.config;

import com.example.fooddelivery.model.Restaurant;
import com.example.fooddelivery.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initRestaurants(RestaurantRepository restaurantRepository) {
        return args -> {
            if (restaurantRepository.count() == 0) {
                restaurantRepository.save(new Restaurant("Pizza Palace", "ул. Пица 1", "0888123456"));
                restaurantRepository.save(new Restaurant("Burger King", "бул. Бургер 5", "0899123456"));
                restaurantRepository.save(new Restaurant("Sushi World", "ул. Суши 9", "0877123456"));
            }
        };
    }
}
