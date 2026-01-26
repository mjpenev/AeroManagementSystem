package com.flightreservation.aero.config;

import com.flightreservation.aero.enums.Role;
import com.flightreservation.aero.model.User;
import com.flightreservation.aero.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    @Bean
    CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            System.out.println("Running DataInitializer...");
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setFirstName("Admin");
                admin.setLastName("User");
                admin.setUsername("admin");
                admin.setEmail("admin@test.com");
                admin.setPassword("admin123");
                admin.setRole(Role.ADMIN);

                userRepository.save(admin);
                System.out.println("Admin user saved!");
            }
        };
    }

}
