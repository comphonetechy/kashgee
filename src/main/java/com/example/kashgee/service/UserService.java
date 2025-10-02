package com.example.kashgee.service;

import com.example.kashgee.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    
    private List<User> users = new ArrayList<>();
    private Long currentId = 1L;
    
    public UserService() {
        // Add default user
        User defaultUser = new User("user", "123", "user@kashgee.com");
        defaultUser.setId(currentId++);
        users.add(defaultUser);
    }
    
    // Register new user
    public boolean registerUser(String username, String password, String email) {
        // Check if username already exists
        if (userExists(username)) {
            return false;
        }
        
        User newUser = new User(username, password, email);
        newUser.setId(currentId++);
        users.add(newUser);
        return true;
    }
    
    // Check if username exists
    public boolean userExists(String username) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }
    
    // Validate login
    public User validateUser(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) 
                             && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
    
    // Get all users (for testing)
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}