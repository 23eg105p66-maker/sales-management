package com.sales.config;

import com.sales.entity.User;
import com.sales.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

// @Component  ❌ DISABLED
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .email("admin@sales.com")
                    .role("ADMIN")
                    .fullName("System Admin")
                    .active(true)
                    .build();
            userRepository.save(admin);
            System.out.println("Default admin user created — username: admin, password: admin123");
        } else {
            System.out.println("Admin user already exists, skipping seed.");
        }
    }
}