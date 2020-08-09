package com.example.controller;

import com.example.model.WeatherReturn;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${key}")
    private String key;
    @Value("${endpoint}")
    private String endpoint;

    @GetMapping(path="/getWeather")
    public ResponseEntity<WeatherReturn> getWeather (@RequestParam(value="q") String city) {
        WeatherReturn weather = new WeatherReturn();
        try {
            String res = restTemplate.getForObject(endpoint+"?q=" + city + "&appid="+key, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(res);
            weather.setTemp(jsonNode.get("main").get("temp").asText());
            weather.setFeels_like(jsonNode.get("main").get("feels_like").asText());

        }catch (Exception e) {
            return new ResponseEntity<WeatherReturn>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<WeatherReturn>(weather,HttpStatus.OK);
    }


}
