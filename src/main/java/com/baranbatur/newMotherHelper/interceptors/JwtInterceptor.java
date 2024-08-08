package com.baranbatur.newMotherHelper.interceptors;

import com.baranbatur.newMotherHelper.service.abstracts.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private final IUserService userService;

    @Autowired
    public JwtInterceptor(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        String token = header.substring(7);
        Integer userId = userService.getUserIdFromToken(token);
        request.setAttribute("userId", userId);
        return true;

    }
}
