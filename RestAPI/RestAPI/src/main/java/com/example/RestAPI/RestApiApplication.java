package com.example.RestAPI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class RestApiApplication  implements CommandLineRunner {

    @Autowired
    private WeatherService weatherService;

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication
.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        weatherService.getWeather("London"); // You can change city name here
    }
}
