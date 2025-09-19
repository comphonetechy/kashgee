package com.example.helloworldapp.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
    public static int totalamount = 10000000;
    public static List<String> transactionLog = new ArrayList<>();
    @GetMapping("/")
    public String home() {
        return "redirect:/bankingapp"; // redirect to /bankingapp
    }

    @GetMapping("/bankingapp")
    public String bankingapp(Model model) {
        model.addAttribute("amount", totalamount);
        return "index"; // loads index.html in templates
    }

    @GetMapping("/getExpressSend")
    public String getExpressSend(Model model) {
        return "send"; // loads send.html
    }

 @PostMapping("/postExpressSend")
    public String postExpressSend(@RequestParam("amount") int amount, @RequestParam("name") String recieverName, Model model) {
        totalamount -= amount;  // update global balance
      

        model.addAttribute("amount", totalamount);

        // Add transaction log entry
        transactionLog.add("Sent ₱" + amount + " | Remaining: ₱" + totalamount + " to " + recieverName);

        // Pass log to the view if you want to display it
        model.addAttribute("transactionLog", transactionLog);

        return "index";
    }
}
