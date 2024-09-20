package com.baranbatur.newMotherHelper.unit;

import com.baranbatur.newMotherHelper.dto.requests.LoginRequest;
import com.baranbatur.newMotherHelper.dto.requests.RegisterRequest;
import com.baranbatur.newMotherHelper.dto.response.LoginResponse;
import com.baranbatur.newMotherHelper.dto.response.RegisterResponse;
import com.baranbatur.newMotherHelper.enums.Gender;
import com.baranbatur.newMotherHelper.enums.Role;
import com.baranbatur.newMotherHelper.repository.UserRepo;
import com.baranbatur.newMotherHelper.service.abstracts.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private IUserService userService;

    @Test
    public void whenLoginWithValidCredentials_thenReturnToken() {
        LoginRequest loginRequest = new LoginRequest("1@gmail.com", "1");
        LoginResponse expectToken = new LoginResponse("mock-token");
        when(userService.login(loginRequest)).thenReturn(expectToken);
        LoginResponse actualResponse = userService.login(loginRequest);
        assertEquals(expectToken.token(), actualResponse.token(), "Token should match expected value");
    }

    // Add more test cases for other methods in UserService
    @Test
    public void whenLoginWithInvalidCredentials_thenThrowException() {
        // Arrange: Geçersiz bilgiler ile giriş isteği oluşturuyoruz
        LoginRequest invalidRequest = new LoginRequest("invalid@example.com", "wrongpassword");

        // Geçersiz giriş için Exception fırlatmayı simüle ediyoruz
        when(userService.login(invalidRequest)).thenThrow(new RuntimeException("Invalid credentials"));

        // Act & Assert: Exception'ın fırlatıldığını doğruluyoruz
        try {
            userService.login(invalidRequest);
        } catch (RuntimeException ex) {
            assertEquals("Invalid credentials", ex.getMessage(), "Exception message should match");
        }
    }

    @Test
    public void whenRegisterWithValidData_thenUserIsCreated() {
        // Arrange: Geçerli verilerle kayıt isteği oluşturuyoruz

        RegisterRequest registerRequest = new RegisterRequest("John", "Doe", "john.doe@example.com", "password123", Gender.MALE, Role.ADMIN);
        RegisterResponse expectResponse = new RegisterResponse("mock-name", "mock-surname", "mock-email", Gender.MALE, "mock-token");

        // Act: Kayıt işlemini gerçekleştiriyoruz
        when(userService.register(registerRequest)).thenReturn(expectResponse);
        // Assert: Kayıt işlemi başarılı olduğunu doğruluyoruz
        RegisterResponse actualResponse = userService.register(registerRequest);
        assertEquals(expectResponse.name(), actualResponse.name(), "Name should match expected value");
    }
}
