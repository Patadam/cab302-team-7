package com.wellnessapp;

import com.wellnessapp.model.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("John", "password");
    }
    @Test
    public void testGetUsername() {
        String username = user.getUsername();
        assertEquals("John", username);
    }
}
