package com.example.currencycalculator.controller;

import com.example.currencycalculator.Service.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/getExchangeRate")
    @ResponseBody
    public double getExchangeRate(@RequestParam String country) {
        return homeService.getExchangeRate(country);
    }

}
