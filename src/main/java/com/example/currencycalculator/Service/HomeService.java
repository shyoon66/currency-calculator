package com.example.currencycalculator.Service;

import com.example.currencycalculator.util.LiveResponseDemo;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    public double getExchangeRate(String country) {
        return LiveResponseDemo.sendLiveRequest(country);
    }
}
