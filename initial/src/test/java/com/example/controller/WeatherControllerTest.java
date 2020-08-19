package com.example.controller;

import com.example.consumingrest.ConsumingRestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(classes = ConsumingRestApplication.class)
@AutoConfigureMockMvc
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WeatherController weatherController;

    @Test
    public void getWeatherTest() throws Exception{
            this.mockMvc.perform(get("/getWeather?q=London")).andExpect(status().isOk());
            this.mockMvc.perform(get("/getWeather?q=Shirala")).andExpect(status().isNotFound());
    }

    @Test
    public void convertTest() {
       assertThat(weatherController.convert(277)).isEqualTo("38");
       assertThat(weatherController.convert(302)).isEqualTo("83");
    }


}