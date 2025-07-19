package com.example.RestAPI;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {
	public Map<String, Object> getWeather(String city) {
		String apiKey = "d354c7fe67242f85bce91571026cbf86";
		String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(url, String.class);

		JSONObject obj = new JSONObject(response);
		String cityName = obj.getString("name");
		double temp = obj.getJSONObject("main").getDouble("temp");
		String weather = obj.getJSONArray("weather").getJSONObject(0).getString("description");

		Map<String, Object> data = new HashMap<>();
		data.put("city", cityName);
		data.put("temperature", temp);
		data.put("description", weather);

		return data;
	}
}
