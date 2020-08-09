package com.example.consumingrest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    private static final Logger logger = Logger.getLogger("MyCommandLineRunner");

    @Value("${city}")
    private String city;
    @Value("${key}")
    private String key;
    @Value("${endpoint}")
    private String endpoint;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
        String res = restTemplate.getForObject(endpoint+"?q="+city+"&appid="+key,String.class);
        logger.info(res);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node =objectMapper.readTree(res);
        logger.info(node.get("main").get("temp").asText());
        logger.info(node.get("weather").get(0).get("description").asText());
    }
}
