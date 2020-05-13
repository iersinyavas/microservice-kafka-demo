package com.microservice.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/customer-sale")
    public String customerSale(Model model) {
        return "customer-sale";
    }
}
