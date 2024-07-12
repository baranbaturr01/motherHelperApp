package com.baranbatur.newMotherHelper.service.concrete;

import com.baranbatur.newMotherHelper.converter.GenericConverter;
import com.baranbatur.newMotherHelper.dto.requests.LoginRequest;
import com.baranbatur.newMotherHelper.dto.requests.RegisterRequest;
import com.baranbatur.newMotherHelper.dto.response.LoginResponse;
import com.baranbatur.newMotherHelper.dto.response.RegisterResponse;
import com.baranbatur.newMotherHelper.model.User;
import com.baranbatur.newMotherHelper.repository.UserRepo;
import com.baranbatur.newMotherHelper.service.abstracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepo userRepository;
    private final GenericConverter<User, LoginRequest> loginRequestConverter;
    private final GenericConverter<User, LoginResponse> loginResponseConverter;
    private final GenericConverter<User, RegisterRequest> registerRequestConverter;
    private final GenericConverter<User, RegisterResponse> registerResponseConverter;

    @Autowired
    public UserServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
        this.loginRequestConverter = new GenericConverter<>(
                user -> new LoginRequest(user.getEmail(), user.getPassword()),
                request -> {
                    User user = new User();
                    user.setEmail(request.email());
                    user.setPassword(request.password());
                    return user;
                }
        );
        this.loginResponseConverter = new GenericConverter<>(
                user -> new LoginResponse(user.getEmail()),
                response -> {
                    User user = new User();
                    user.setEmail(response.email());
                    return user;
                }
        );
        this.registerRequestConverter = new GenericConverter<>(
                user -> new RegisterRequest(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getGender()),
                request -> {
                    User user = new User();
                    user.setName(request.name());
                    user.setPassword(request.password());
                    user.setEmail(request.email());
                    user.setGender(request.gender());
                    return user;
                }
        );
        this.registerResponseConverter = new GenericConverter<>(
                user -> new RegisterResponse(user.getName(), user.getSurname(), user.getEmail(), user.getGender()),
                response -> {
                    User user = new User();
                    user.setEmail(response.name());
                    user.setSurname(response.surname());
                    return user;
                }
        );
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmailAndPassword(
                loginRequest.email(),
                loginRequest.password()
        );
        if (user == null) {
            throw new RuntimeException("Invalid credentials");
        }
        return loginResponseConverter.convertToDto(user);
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = registerRequestConverter.convertToEntity(registerRequest);
        user = userRepository.save(user);
        return registerResponseConverter.convertToDto(user);
    }
}
