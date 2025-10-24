
package com.example.kashgee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.kashgee.model.Transaction;
import com.example.kashgee.service.BankingService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    
    @Autowired
    private BankingService bankingService;
    
    // Check if user is logged in
    private boolean isLoggedIn(HttpSession session) {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        return loggedIn != null && loggedIn;
    }
    
    @GetMapping("/")
    public String home(HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        return "redirect:/bankingapp";
    }

    @GetMapping("/bankingapp")
    public String bankingapp(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        
        int balance = bankingService.getBalance();
        List<Transaction> transactions = bankingService.getTransactionHistory();
        
        model.addAttribute("amount", balance);
        model.addAttribute("transactionLog", transactions);
        model.addAttribute("username", session.getAttribute("username"));
        
        return "index";
    }

    @GetMapping("/getExpressSend")
    public String getExpressSend(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        
        model.addAttribute("currentBalance", bankingService.getBalance());
        return "send";
    }

    @PostMapping("/postExpressSend")
    public String postExpressSend(
            HttpSession session,
            @RequestParam("amount") int amount, 
            @RequestParam("name") String receiverName, 
            RedirectAttributes redirectAttributes) {
        
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        
        try {
            bankingService.sendMoney(amount, receiverName);
            redirectAttributes.addFlashAttribute("successMessage", 
                "Successfully sent ₱" + amount + " to " + receiverName);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        
        return "redirect:/bankingapp";
    }


 @GetMapping("/getDeposit")
    public String getDeposit(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        
  
        return "deposit";
    }


    @PostMapping("/postDeposit")
    public String postDeposit(
            HttpSession session,
            @RequestParam("amount") int amount,
            RedirectAttributes redirectAttributes) {
        
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        
        try {
            bankingService.depositMoney(amount);
            redirectAttributes.addFlashAttribute("successMessage", 
                "Successfully deposited ₱" + amount);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        
        return "redirect:/bankingapp";
    }

}