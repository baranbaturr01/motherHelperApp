package com.baranbatur.newMotherHelper.service;

import com.baranbatur.newMotherHelper.converter.GenericConverter;
import com.baranbatur.newMotherHelper.dto.requests.LoginRequest;
import com.baranbatur.newMotherHelper.dto.requests.RegisterRequest;
import com.baranbatur.newMotherHelper.dto.response.LoginResponse;
import com.baranbatur.newMotherHelper.dto.response.RegisterResponse;
import com.baranbatur.newMotherHelper.enums.Role;
import com.baranbatur.newMotherHelper.model.User;
import com.baranbatur.newMotherHelper.repository.UserRepo;
import com.baranbatur.newMotherHelper.service.JwtService;
import com.baranbatur.newMotherHelper.service.abstracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepo userRepository;

    private final PasswordEncoder passwordEncoder;
    private final Role role;
    private final GenericConverter<User, LoginRequest> loginRequestConverter;
    private final GenericConverter<User, LoginResponse> loginResponseConverter;
    private final GenericConverter<User, RegisterRequest> registerRequestConverter;
    private final GenericConverter<User, RegisterResponse> registerResponseConverter;

    private final JwtService jwtService;

    @Autowired
    public UserServiceImpl(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = new JwtService();
        this.role = Role.ADMIN;
        this.loginRequestConverter = new GenericConverter<>(user -> new LoginRequest(user.getEmail(), user.getPassword()), request -> {
            User user = new User();
            user.setEmail(request.email());
            user.setPassword(request.password());
            user.setRole(role);
            return user;
        });
        this.loginResponseConverter = new GenericConverter<>(user -> new LoginResponse(null), response -> {
            User user = new User();
            user.setEmail(response.token());
            return user;
        });
        this.registerRequestConverter = new GenericConverter<>(user -> new RegisterRequest(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getGender(), role), request -> {
            User user = new User();
            user.setName(request.getName());
            user.setSurname(request.getSurname());
            user.setPassword(request.getPassword());
            user.setEmail(request.getEmail());
            user.setGender(request.getGender());
            user.setRole(role);
            return user;
        });
        this.registerResponseConverter = new GenericConverter<>(user -> new RegisterResponse(user.getName(), user.getSurname(), user.getEmail(), user.getGender(), null), response -> {
            User user = new User();
            user.setEmail(response.email());
            user.setSurname(response.surname());
            return user;
        });
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.email());

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User newUser = user.get();

        if (!passwordEncoder.matches(loginRequest.password(), newUser.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(newUser);
        return new LoginResponse(token);
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = registerRequestConverter.convertToEntity(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(role);
        user = userRepository.save(user);
        var token = jwtService.generateToken(user);
        RegisterResponse response = registerResponseConverter.convertToDto(user);
        return new RegisterResponse(response.name(), response.surname(), response.email(), response.gender(), token);
    }

    @Override
    public Integer getUserIdFromToken(String token) {
        String email = jwtService.extractUsername(token);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return user.getId();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Boolean deleteUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return true;
    }
}
