package com.baranbatur.newMotherHelper.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.BufferedReader;
import java.io.IOException;

@Aspect
@Component
public class Logging {

    @Before("execution(* com.baranbatur.newMotherHelper.controller.*.*(..))")
    public void logBeforeControllerMethods() {
        System.err.println("Controller method called.");
    }

    @After("execution(* com.baranbatur.newMotherHelper.controller.*.*(..))")
    public void logAfterControllerMethods() {
        System.err.println("Controller method finished.");
    }
}
