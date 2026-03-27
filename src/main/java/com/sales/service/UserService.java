package com.sales.service;

import com.sales.dto.SalesDtos.*;
import com.sales.entity.User;
import com.sales.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return toResponse(user);
    }

    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("Username already exists: " + request.getUsername());
        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email already exists: " + request.getEmail());

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(request.getRole() != null ? request.getRole() : "SALES_REP")
                .fullName(request.getFullName())
                .active(true)
                .build();

        return toResponse(userRepository.save(user));
    }

    public UserResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if (request.getFullName() != null) user.setFullName(request.getFullName());
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getRole() != null) user.setRole(request.getRole());
        if (request.getPassword() != null && !request.getPassword().isBlank())
            user.setPassword(passwordEncoder.encode(request.getPassword()));

        return toResponse(userRepository.save(user));
    }

    public void deactivateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setActive(false);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id))
            throw new RuntimeException("User not found with id: " + id);
        userRepository.deleteById(id);
    }

    public List<UserResponse> getUsersByRole(String role) {
        return userRepository.findByRole(role)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public UserResponse authenticate(LoginRequest request) {
        if (request.getUsername() == null || request.getUsername().isBlank() ||
                request.getPassword() == null || request.getPassword().isBlank()) {
            throw new RuntimeException("Username and password are required");
        }

        User user = userRepository.findByUsername(request.getUsername().trim())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!user.isActive()) {
            throw new RuntimeException("This user account is deactivated.");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return toResponse(user);
    }

    private UserResponse toResponse(User user) {
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setUsername(user.getUsername());
        res.setEmail(user.getEmail());
        res.setRole(user.getRole());
        res.setFullName(user.getFullName());
        res.setActive(user.isActive());
        return res;
    }
}
