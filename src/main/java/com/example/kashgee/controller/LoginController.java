
package com.example.kashgee.controller;

import com.example.kashgee.model.User;
import com.example.kashgee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {
        
        User user = userService.validateUser(username, password);
        
        if (user != null) {
            session.setAttribute("loggedIn", true);
            session.setAttribute("username", username);
            session.setAttribute("userId", user.getId());
            return "redirect:/bankingapp";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            Model model) {
        
        // Validation
        if (username.trim().isEmpty() || password.trim().isEmpty() || email.trim().isEmpty()) {
            model.addAttribute("error", "All fields are required");
            return "register";
        }
        
        if (password.length() < 3) {
            model.addAttribute("error", "Password must be at least 3 characters");
            return "register";
        }
        
        boolean success = userService.registerUser(username, password, email);
        
        if (success) {
            model.addAttribute("success", "Registration successful! Please login.");
            return "login";
        } else {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}