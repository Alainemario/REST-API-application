package com.example.RestAPI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.Desktop;
import java.net.URI;

@SpringBootApplication
public class RestApiApplication implements CommandLineRunner {

    @Autowired
    private WeatherService weatherService;

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String city = "London";  // you can change this
        weatherService.getWeather(city);

        String url = "http://localhost:8080/weather?city=" + city;

        try {
            // Try Desktop first
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                openBrowserFallback(url);
            }
        } catch (Exception e) {
            System.err.println("Failed to open browser: " + e.getMessage());
            openBrowserFallback(url);
        }
    }

    private void openBrowserFallback(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                
                new ProcessBuilder("rundll32", "url.dll,FileProtocolHandler", url).start();
            } else if (os.contains("mac")) {
                
                new ProcessBuilder("open", url).start();
            } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                
                new ProcessBuilder("xdg-open", url).start();
            } else {
                System.out.println("Please open the following URL manually: " + url);
            }
        } catch (Exception ex) {
            System.out.println("Could not launch browser automatically. Open this URL: " + url);
        }
    }
}
