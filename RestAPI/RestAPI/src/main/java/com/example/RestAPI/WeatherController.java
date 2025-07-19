package com.example.RestAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public String showWeather(@RequestParam(defaultValue = "London") String city, Model model) {
        Map<String, Object> data = weatherService.getWeather(city);
        model.addAllAttributes(data);
        return "weather";
    }
}
