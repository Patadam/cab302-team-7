package com.wellnessapp;

import com.wellnessapp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User user;
    @BeforeEach
    public void setUp() {
        user = new User("example@example.com", "account123");
    }
    @Test
    public void testGetEmail() {
        assertEquals("example@example.com", user.getEmail());
    }
    @Test
    public void testGetPassword() {
        assertEquals("account123", user.getPassword());
    }
//    @Test
//    public void testSetEmail() {
//        user.setEmail("jane.smith@example.com");
//        assertEquals("jane.smith@example.com", contact.getEmail());
//    }

}
