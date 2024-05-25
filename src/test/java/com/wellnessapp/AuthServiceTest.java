package com.wellnessapp;

import com.wellnessapp.model.User;
import com.wellnessapp.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {
    private AuthService service;
    @BeforeEach
    public void setupTest(){
        service = AuthService.getInstance();
    }
    @Test
    public void testGetInstance() {
        AuthService service = AuthService.getInstance();
        assertNotNull(service);
    }
    @Test
    public void testSetAndGetUser() {
        User user = new User("myuser@myuser.com", "password");
        service.setCurrentUser(user);
        assertEquals(user, service.getCurrentUser());
    }
    @Test
    public void testSingletonInstance() {
        AuthService service1 = AuthService.getInstance();
        AuthService service2 = AuthService.getInstance();
        assertSame(service1, service2);
    }
}